package com.doce.cactus.saba.jetweatherforecast.di

import android.content.Context
import androidx.room.Room
import com.doce.cactus.saba.jetweatherforecast.data.WeatherDao
import com.doce.cactus.saba.jetweatherforecast.data.WeatherDatabase
import com.doce.cactus.saba.jetweatherforecast.repository.network.WeatherApi
import com.doce.cactus.saba.jetweatherforecast.repository.WeatherDbRepository
import com.doce.cactus.saba.jetweatherforecast.repository.WeatherDbRepositoryImpl
import com.doce.cactus.saba.jetweatherforecast.repository.WeatherRepository
import com.doce.cactus.saba.jetweatherforecast.repository.WeatherRepositoryImpl
import com.doce.cactus.saba.jetweatherforecast.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao =
        weatherDatabase.weatherDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): WeatherDatabase{
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "weather_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideOpenWeatherAPI(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: WeatherApi): WeatherRepository{
        return WeatherRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideRepositoryDB(dao: WeatherDao): WeatherDbRepository{
        return WeatherDbRepositoryImpl(dao)
    }



}