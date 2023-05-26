package com.doce.cactus.saba.jetweatherforecast.screens.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.doce.cactus.saba.jetweatherforecast.model.Favorite
import com.doce.cactus.saba.jetweatherforecast.navigation.WeatherScreens
import com.doce.cactus.saba.jetweatherforecast.widgets.WeatherAppBar

@Composable
fun FavoriteScreen(
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
){
    Scaffold(
        topBar = {
            WeatherAppBar(
                navController = navController,
                title = "Favorite Cities",
                icon = Icons.Default.ArrowBack,
                isMainScreen = false,
            ){
                navController.popBackStack()
            }
        }
    ) {
        it
        Surface(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()

        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val list = favoriteViewModel.favList.collectAsState().value
                LazyColumn{
                    items(items = list){
                        CityRow(item = it, navController = navController, favoriteViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun CityRow(
    item: Favorite,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel) {
    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                       navController.navigate(WeatherScreens.MainScreen.name + "/${item.city}")
            },
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color(0xFFB2DFD8)
    ) {
       Row(
           Modifier.fillMaxWidth(),
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.SpaceEvenly
       ) {
           Text(text = item.city, modifier = Modifier.padding(start = 4.dp))
           Surface(
               modifier = Modifier.padding(0.dp),
               shape = CircleShape,
               color = Color(0xFFD1E3E1)
           ) {
               Text(
                   text = item.country,
                   modifier = Modifier.padding(4.dp),
                   style = MaterialTheme.typography.caption
               )
           }
           Icon(
               imageVector = Icons.Rounded.Delete,
               contentDescription = "delete",
               modifier = Modifier.clickable {
                    favoriteViewModel.deleteFavorite(item)
               },
               tint = Color.Red.copy(alpha = 0.3f)
               )
       }
    }
}
