package com.doce.cactus.saba.jetweatherforecast.screens.main

import androidx.lifecycle.ViewModel
import com.doce.cactus.saba.jetweatherforecast.data.DataOrException
import com.doce.cactus.saba.jetweatherforecast.model.Weather
import com.doce.cactus.saba.jetweatherforecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository): ViewModel() {



    suspend fun getWeather(city: String): DataOrException<Weather,Boolean,Exception> {
        return repository.getWeather(cityQuery = city)

    }


}