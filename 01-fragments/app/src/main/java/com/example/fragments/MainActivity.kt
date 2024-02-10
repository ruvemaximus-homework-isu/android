package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


class MainActivity : AppCompatActivity() {
    private var curFragment: Int? = null
    private lateinit var fm: FragmentManager

    private lateinit var detailedWeatherFragment: Fragment
    private lateinit var compactWeatherFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fm = supportFragmentManager

        detailedWeatherFragment = DetailedWeatherFragment.newInstance(0)
        compactWeatherFragment = CompactWeatherFragment.newInstance(0)

        fm.beginTransaction()
            .replace(R.id.weatherFragment, detailedWeatherFragment)
            .commit()

        findViewById<androidx.fragment.app.FragmentContainerView>(R.id.weatherFragment)
            .setOnClickListener {
                curFragment = when(curFragment) {
                    0 -> 1
                    1 -> 0
                    else -> 1
                }

                fm.beginTransaction()
                    .replace(R.id.weatherFragment, when(curFragment) {
                        0 -> detailedWeatherFragment
                        1 -> compactWeatherFragment
                        else -> detailedWeatherFragment
                    })
                    .commit()

            }
    }
}