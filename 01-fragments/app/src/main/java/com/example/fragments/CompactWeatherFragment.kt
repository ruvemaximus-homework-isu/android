package com.example.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.fragments.databinding.FragmentCompactWeatherBinding

private const val ARG_WEATHER_DATA_ID = "weather_data_id"


class CompactWeatherFragment : Fragment() {
    private var weatherId: Int = 0
    private var repository: WeatherMockRepository? = null

    private var _binding: FragmentCompactWeatherBinding? = null
    private val binding get() = _binding!!

    private var _data: WeatherData? = null
    private val data get() = _data!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompactWeatherBinding.inflate(inflater, container, false)

        binding.apply {
            title.text = getString(R.string.compact_info, data.name)
            temp.text = getString(
                R.string.temperature_info_compact,
                data.main.temp.toString(),
            )
            wind.text = getString(
                R.string.wind_info_compact,
                data.main.temp.toString(),
            )
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            weatherId = it.getInt(ARG_WEATHER_DATA_ID)
        }

        repository = WeatherMockRepository()
        _data = repository!!.getById(weatherId)
    }

    companion object {
        fun newInstance(id: Int) =
            CompactWeatherFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_WEATHER_DATA_ID, id)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
