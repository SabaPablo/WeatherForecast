package com.doce.cactus.saba.jetweatherforecast.repository

import com.doce.cactus.saba.jetweatherforecast.model.Favorite
import kotlinx.coroutines.flow.Flow
import com.doce.cactus.saba.jetweatherforecast.model.Unit

interface WeatherDbRepository {

    fun getFavorites(): Flow<List<Favorite>>

    suspend fun insertFavorite(favorite:Favorite)

    suspend fun updateFavorite(favorite:Favorite)

    suspend fun deleteFavorite(favorite:Favorite)

    suspend fun deleteAllFavorites()

    suspend fun getFavById(city:String): Favorite?


    // Unit

    fun getUnits(): Flow<List<Unit>>


    suspend fun insertUnit(unit:Unit)

    suspend fun deleteUnit(unit:Unit)

    suspend fun deleteAllUnits()

}