package com.example.fragments

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.fragments.databinding.ActivityMainBinding
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val fragments = mapOf(
        0 to DetailedWeatherFragment.newInstance(0),
        1 to CompactWeatherFragment.newInstance(0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val titles = getResources().getStringArray(R.array.weather_types)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, titles)

        val dialog = AlertDialog.Builder(this).apply {
            setTitle(getString(R.string.weather_change_dialog))
            setAdapter(adapter) { _, which -> switchToFragment(which) }
            create()
        }

        binding.apply {
            toolbar.apply {
                setTitle(R.string.language)
                inflateMenu(R.menu.languages)
                setOnMenuItemClickListener { item ->
                    val locale = Locale(item.toString())

                    Locale.setDefault(locale)
                    val configuration = baseContext.resources.configuration
                    configuration.setLocale(locale)
                    baseContext.createConfigurationContext(configuration)

                    recreate()

                    true
                }
            }
            weatherFragment.setOnClickListener { dialog.show() }
        }

        switchToFragment(0)
    }

    private fun switchToFragment(id: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.weatherFragment, fragments[id]!!)
            .commit()
    }
}