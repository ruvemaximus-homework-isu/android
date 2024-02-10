package com.example.fragments

import com.google.gson.Gson

const val WEATHER_JSON = """
{
  "coord": {
    "lon": 104,
    "lat": 52
  },
  "weather": [
    {
      "id": 803,
      "main": "Clouds",
      "description": "broken clouds",
      "icon": "04d"
    }
  ],
  "base": "stations",
  "main": {
    "temp": 275.93,
    "feels_like": 273.46,
    "temp_min": 275.81,
    "temp_max": 275.93,
    "pressure": 1012,
    "humidity": 77,
    "sea_level": 1012,
    "grnd_level": 930
  },
  "visibility": 10000,
  "wind": {
    "speed": 2.46,
    "deg": 197,
    "gust": 6
  },
  "clouds": {
    "all": 71
  },
  "dt": 1707552155,
  "sys": {
    "type": 1,
    "id": 8891,
    "country": "RU",
    "sunrise": 1707525025,
    "sunset": 1707559567
  },
  "timezone": 28800,
  "id": 2052079,
  "name": "Podkamennaya",
  "cod": 200
}
"""

class WeatherMockRepository {
    private val data = mutableMapOf(
        0 to Gson().fromJson(WEATHER_JSON, WeatherData::class.java)
    )

    fun getById(id: Int): WeatherData {
        return data[id] ?: throw Exception("Data not found")
    }
}