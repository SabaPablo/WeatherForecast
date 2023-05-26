package com.doce.cactus.saba.jetweatherforecast.repository

import com.doce.cactus.saba.jetweatherforecast.data.DataOrException
import com.doce.cactus.saba.jetweatherforecast.model.Weather
import com.doce.cactus.saba.jetweatherforecast.repository.network.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.util.*
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val api: WeatherApi): WeatherRepository {

    override suspend fun getWeather(cityQuery: String): DataOrException<Weather,Boolean,Exception>{


        val response = try {
            api.getWeather(cityQuery)
        }catch (e: Exception){
            e.printStackTrace()
            return DataOrException(e=e)
        }
        return DataOrException(data = response,false,null)
    }
}