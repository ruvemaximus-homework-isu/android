package com.example.tabbedactivity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tabbedactivity.databinding.FragmentCityWeatherBinding

private const val ARG_ID = "id"

/**
 * A simple [Fragment] subclass.
 * Use the [CityWeatherFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CityWeatherFragment : Fragment() {
    private var id: Int? = null
    private var _binding: FragmentCityWeatherBinding? = null
    private val binding get() = _binding!!

    private val repository = CityWeatherRepository()
    private var cityWeatherData: CityWeatherData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ARG_ID)
            cityWeatherData = repository.get(id)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCityWeatherBinding.inflate(inflater, container, false)

        binding.apply {
            cityName.text = cityWeatherData?.cityName
            temperature.text = cityWeatherData?.temperature.toString()
            humidity.text = cityWeatherData?.humidity.toString()
            windSpeed.text = cityWeatherData?.windSpeed.toString()
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int) =
            CityWeatherFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID, id)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}