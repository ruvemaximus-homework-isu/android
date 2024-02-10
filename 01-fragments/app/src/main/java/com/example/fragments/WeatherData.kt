package com.example.fragments

data class WeatherMainData(
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int
)

data class WeatherWindData(
    val speed: Double,
    val deg: Int,
    val gust: Int
)

data class WeatherData(
    val name: String,
    val wind: WeatherWindData,
    val main: WeatherMainData
)