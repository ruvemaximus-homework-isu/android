package com.example.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


private const val ARG_WEATHER_DATA_ID = "weather_data_id"


class CompactWeatherFragment : Fragment() {
    private var weatherId: Int = 0
    private var repository = WeatherMockRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_compact_weather, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            weatherId = it.getInt(ARG_WEATHER_DATA_ID)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = repository.getById(weatherId)

        view.findViewById<TextView>(R.id.title).text = "Compact Info (${data.name})"
        view.findViewById<TextView>(R.id.temp).text = "[Temp] ${data.main.temp}"
        view.findViewById<TextView>(R.id.wind).text = "[Wind] Speed: ${data.wind.speed} (${data.wind.deg})"
    }

    companion object {
        fun newInstance(id: Int) =
            CompactWeatherFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_WEATHER_DATA_ID, id)
                }
            }
    }
}
