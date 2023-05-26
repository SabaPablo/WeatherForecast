package com.doce.cactus.saba.jetweatherforecast.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doce.cactus.saba.jetweatherforecast.model.Favorite
import com.doce.cactus.saba.jetweatherforecast.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: WeatherDbRepository) :ViewModel() {

    private val _favList = MutableStateFlow<List<Favorite>>(emptyList())
    var favList = _favList.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavorites().catch {  e ->
                e.printStackTrace()
            }.collect{ listFavorites ->
                _favList.value = listFavorites
            }
        }
    }



    fun insertFavorite(favorite: Favorite) =
        viewModelScope.launch {
            repository.insertFavorite(favorite)
        }
    fun deleteFavorite(favorite: Favorite) =
        viewModelScope.launch {
            repository.deleteFavorite(favorite)
        }

    fun isAlreadyFavList(city: Favorite?): Boolean {
        return city != null && favList.value.filter { it.city == city.city && it.country == city.country  }.isNotEmpty()
    }

}