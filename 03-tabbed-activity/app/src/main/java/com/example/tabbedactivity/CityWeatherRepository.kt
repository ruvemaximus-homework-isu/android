package com.example.tabbedactivity

private val cityWeatherData = mapOf(
    0 to CityWeatherData(cityName = "Irkutsk"),
    1 to CityWeatherData(cityName = "Moscow"),
    2 to CityWeatherData(cityName = "London")
)

class CityWeatherRepository {
    fun get(position: Int?) : CityWeatherData {
        return cityWeatherData[position]!!
    }
    fun count() : Int {
        return cityWeatherData.size
    }
}