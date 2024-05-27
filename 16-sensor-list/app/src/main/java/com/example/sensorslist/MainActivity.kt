package com.example.sensorslist

import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var sensorManager: SensorManager
    private lateinit var sensorAdapter: SensorAdapter
    private lateinit var sensorRecyclerView: RecyclerView
    private lateinit var sensorCategorySpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorRecyclerView = findViewById(R.id.sensorRecyclerView)
        sensorCategorySpinner = findViewById(R.id.sensorCategorySpinner)

        val categories = resources.getStringArray(R.array.sensor_categories)
        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sensorCategorySpinner.adapter = categoryAdapter

        sensorRecyclerView.layoutManager = LinearLayoutManager(this)

        sensorCategorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedCategory = categories[position]
                displaySensors(selectedCategory)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun displaySensors(category: String) {
        val sensors = when (category) {
            "Датчики окружающей среды" -> getSensorsByType(
                Sensor.TYPE_AMBIENT_TEMPERATURE,
                Sensor.TYPE_LIGHT,
                Sensor.TYPE_PRESSURE,
                Sensor.TYPE_RELATIVE_HUMIDITY
            )

            "Датчики положения устройства" -> getSensorsByType(
                Sensor.TYPE_ACCELEROMETER,
                Sensor.TYPE_GYROSCOPE,
                Sensor.TYPE_MAGNETIC_FIELD,
                Sensor.TYPE_PROXIMITY
            )

            "Датчики состояния человека" -> getSensorsByType(
                Sensor.TYPE_HEART_RATE,
                Sensor.TYPE_STEP_COUNTER
            )

            else -> emptyList()
        }

        sensorAdapter = SensorAdapter(sensors)
        sensorRecyclerView.adapter = sensorAdapter
    }

    private fun getSensorsByType(vararg types: Int): List<Sensor> {
        val sensorList = mutableListOf<Sensor>()
        types.forEach { type ->
            sensorList.addAll(sensorManager.getSensorList(type))
        }
        return sensorList
    }
}
