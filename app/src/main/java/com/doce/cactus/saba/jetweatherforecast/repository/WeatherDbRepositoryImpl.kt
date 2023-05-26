package com.doce.cactus.saba.jetweatherforecast.repository

import com.doce.cactus.saba.jetweatherforecast.data.WeatherDao
import com.doce.cactus.saba.jetweatherforecast.model.Favorite
import com.doce.cactus.saba.jetweatherforecast.model.Unit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepositoryImpl @Inject constructor(private val weatherDao: WeatherDao): WeatherDbRepository {
    override fun getFavorites(): Flow<List<Favorite>> {
        return weatherDao.getFavorites()
    }

    override suspend fun insertFavorite(favorite: Favorite) {
        weatherDao.insertFavorite(favorite)
    }

    override suspend fun updateFavorite(favorite: Favorite) {
        weatherDao.updateFavorite(favorite)

    }

    override suspend fun deleteFavorite(favorite: Favorite) {
        weatherDao.deleteFavorite(favorite)

    }

    override suspend fun deleteAllFavorites() {
        weatherDao.deleteAllFavorites()

    }

    override suspend fun getFavById(city: String): Favorite? {
        return weatherDao.getFavById(city)
    }

    override fun getUnits(): Flow<List<Unit>> {
        return  weatherDao.getUnits()
    }

    override suspend fun insertUnit(unit: Unit) {
        weatherDao.insertUnit(unit)
    }

    override suspend fun deleteUnit(unit: Unit) {
        weatherDao.deleteUnit(unit)
    }

    override suspend fun deleteAllUnits() {
        weatherDao.deleteAllUnits()
    }

}