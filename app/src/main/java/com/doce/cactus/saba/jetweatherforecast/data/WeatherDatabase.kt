package com.doce.cactus.saba.jetweatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.doce.cactus.saba.jetweatherforecast.model.Favorite
import com.doce.cactus.saba.jetweatherforecast.model.Unit

@Database(
    entities = [Favorite::class, Unit::class],
    version = 2,
    exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

}