package com.doce.cactus.saba.jetweatherforecast.repository.network

import com.doce.cactus.saba.jetweatherforecast.model.Weather
import com.doce.cactus.saba.jetweatherforecast.utils.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {

    @GET("data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = API_KEY
    ): Weather

}