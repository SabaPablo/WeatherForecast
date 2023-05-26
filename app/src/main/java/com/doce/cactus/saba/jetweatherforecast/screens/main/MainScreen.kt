package com.doce.cactus.saba.jetweatherforecast.screens.main

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.doce.cactus.saba.jetweatherforecast.data.DataOrException
import com.doce.cactus.saba.jetweatherforecast.model.Weather
import com.doce.cactus.saba.jetweatherforecast.navigation.WeatherScreens
import com.doce.cactus.saba.jetweatherforecast.utils.formatDate
import com.doce.cactus.saba.jetweatherforecast.utils.formatDecimals
import com.doce.cactus.saba.jetweatherforecast.widgets.*

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
    city: String?
) {
    val weatherData = produceState<DataOrException<Weather,Boolean,Exception>>(
        initialValue = DataOrException(loading = true)){
        value = viewModel.getWeather(city?:"Quebec")
    }.value

    if(weatherData.loading == true){
        CircularProgressIndicator()

    }else if(weatherData.data != null){
        MainScaffold(weather= weatherData.data!!, viewModel = viewModel, navController = navController)
    }


}

@Composable
fun MainScaffold(weather: Weather, viewModel: MainViewModel, navController: NavController) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                navController = navController,
                onAddActionClicked = {
                                     navController.navigate(WeatherScreens.SearchScreen.name)
                },
                title = weather.city.name +", ${weather.city.country}",
                elevation = 5.dp,
                onButtonClicked = {
                    Log.d("TAG", "MainScaffold: Button Clicked")
                }
            )
        }
    ) {
        MainContent(data = weather, it)
    }
}

@Composable
fun MainContent(data: Weather, paddingValues: PaddingValues) {
    val weather = data.list.first()
    val imageUrl = "https://openweathermap.org/img/wn/${weather.weather.first().icon}.png"

    Column(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = formatDate(weather.dt.toLong()) ,
        style = MaterialTheme.typography.caption,
        color = MaterialTheme.colors.onSecondary,
        fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp)
        )
        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
            ) {
            Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
                WeatherStateImage(imageUrl)
                Text(text = formatDecimals(weather.temp.day) + "°", style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.ExtraBold)
                Text(text = weather.weather.first().main, fontStyle = FontStyle.Italic)
                
            }
        }
        HumidityWindPresureRow(weather= weather)
        Divider()
        SunriseAndSunSetRow(weather= weather)
        Text(text = "This Week", style = MaterialTheme.typography.subtitle1)
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            color = Color(0xFFEEF1EF),
            shape = RoundedCornerShape(size = 14.dp)
        ) {
            LazyColumn(modifier = Modifier.padding(2.dp),
            contentPadding = PaddingValues(1.dp)
            ){
                items(items= data.list){item ->
                    WeatherItemRow(item = item)
                }
            }
        }
    }
}

