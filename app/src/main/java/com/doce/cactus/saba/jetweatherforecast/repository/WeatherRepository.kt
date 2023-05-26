package com.doce.cactus.saba.jetweatherforecast.repository

import com.doce.cactus.saba.jetweatherforecast.data.DataOrException
import com.doce.cactus.saba.jetweatherforecast.model.Weather

interface WeatherRepository {

    suspend fun getWeather(cityQuery: String): DataOrException<Weather, Boolean, Exception>

}