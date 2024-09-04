package com.example.pwassignment


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pwassignment.ui.screens.DetailsScreen
import com.example.pwassignment.ui.screens.HomeScreen
import com.example.pwassignment.ui.screens.SplashScreen
import com.example.pwassignment.viewmodel.CharactersViewModel
import com.example.pwassignment.viewmodel.DetailsViewModel

sealed class SCREENS(val route:String) {
    object SPLASH : SCREENS("splash_Screen")
    object HOME : SCREENS("home")
    object DETAILS : SCREENS("details/{id}")
}



@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel= hiltViewModel<CharactersViewModel>()
    val detailsViewModel= hiltViewModel<DetailsViewModel>()
    NavHost(navController = navController, startDestination = SCREENS.HOME.route) {
        composable(SCREENS.SPLASH.route) {
            SplashScreen(navController)
        }
        composable(SCREENS.HOME.route) {
            HomeScreen(viewModel = viewModel, navController = navController )

        }
        composable(SCREENS.DETAILS.route, arguments = listOf(navArgument("id"){type= NavType.StringType}))
        {navBackStackEntry->
            val id=navBackStackEntry.arguments?.getString("id")
            DetailsScreen( navController,id,detailsViewModel)

        }
    }
}