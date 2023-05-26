package com.doce.cactus.saba.jetweatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.doce.cactus.saba.jetweatherforecast.screens.about.AboutScreen
import com.doce.cactus.saba.jetweatherforecast.screens.favorite.FavoriteScreen
import com.doce.cactus.saba.jetweatherforecast.screens.main.MainScreen
import com.doce.cactus.saba.jetweatherforecast.screens.main.MainViewModel
import com.doce.cactus.saba.jetweatherforecast.screens.search.SearchScreen
import com.doce.cactus.saba.jetweatherforecast.screens.settings.SettingsScreen
import com.doce.cactus.saba.jetweatherforecast.screens.splash.SplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController= navController,
        startDestination = WeatherScreens.SplashScreen.name){

        composable(WeatherScreens.SplashScreen.name){
            SplashScreen(navController= navController)
        }

        val route =WeatherScreens.MainScreen.name
        composable(
            route = "$route/{city}",
            arguments = listOf(
                navArgument(name = "city"){
                    type = NavType.StringType
                }
            )){ navBack ->
            navBack.arguments?.getString("city").let { city ->
                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController= navController, viewModel = mainViewModel, city = city)
            }

        }
        composable(WeatherScreens.AboutScreen.name){
            AboutScreen(navController= navController)
        }
        composable(WeatherScreens.FavoriteScreen.name){
            FavoriteScreen(navController= navController)
        }
        composable(WeatherScreens.SearchScreen.name){
            SearchScreen(navController= navController)
        }
        composable(WeatherScreens.SettingsScreen.name){
            SettingsScreen(navController= navController)
        }
    }

}