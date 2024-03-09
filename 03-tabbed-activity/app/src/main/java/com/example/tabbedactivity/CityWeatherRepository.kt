package com.example.tabbedactivity

private val cityWeatherData = mapOf(
    0 to CityWeatherData(cityName = "Irkutsk", temperature = -10.5, humidity = 70, windSpeed = 5.5),
    1 to CityWeatherData(cityName = "Moscow", temperature = -5.8, humidity = 85, windSpeed = 3.2),
    2 to CityWeatherData(cityName = "London", temperature = 8.9, humidity = 65, windSpeed = 10.1)
)

class CityWeatherRepository {
    fun get(position: Int?): CityWeatherData? {
        return cityWeatherData[position] ?: cityWeatherData[0]
    }

    fun count(): Int {
        return cityWeatherData.size
    }
}