package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


class MainActivity : AppCompatActivity() {
    private lateinit var fm: FragmentManager

    private lateinit var detailedWeatherFragment: Fragment
    private lateinit var compactWeatherFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fm = supportFragmentManager

        detailedWeatherFragment = DetailedWeatherFragment.newInstance(0)
        compactWeatherFragment = CompactWeatherFragment.newInstance(0)

        val fragments = arrayOf("Подробная погода", "Краткая сводка")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, fragments)

        val dialog = AlertDialog.Builder(this).apply {
            setTitle("Как отобразить погоду?")
            setAdapter(adapter) { _, which ->
                when (which) {
                    0 -> switchToFragment(detailedWeatherFragment)
                    1 -> switchToFragment(compactWeatherFragment)
                }
            }.create()
        }

        findViewById<androidx.fragment.app.FragmentContainerView>(R.id.weatherFragment).setOnClickListener { dialog.show() }
    }

    private fun switchToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.weatherFragment, fragment).commit()
    }
}