package com.hackathon.navigation


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.hackathon.data.dto.GlobalEventsScreen
import com.hackathon.data.dto.GlobalEventsViewModel
import com.hackathon.emergency.EmergencyContactViewModel
import com.hackathon.emergency.EmergenyContactScreen
import com.hackathon.screens.AddDisasterScreen
import com.hackathon.screens.BottomNavScreen
import com.hackathon.screens.DisasterDetails
import com.hackathon.screens.HomeScreen
import com.hackathon.screens.LoginScreen
import com.hackathon.screens.MapScreen
import com.hackathon.screens.ProfileScreen
import com.hackathon.screens.RegisterScreen
import com.hackathon.screens.SplashScreen


sealed class NavRoutes(val route:String){

    data object Home:NavRoutes("Home")
    data object Login:NavRoutes("Login")
    data object Register:NavRoutes("Register")

    data object BottomNav:NavRoutes("BottomNav")

    data object Search:NavRoutes("Search")

    data object Splash:NavRoutes("Splash")

    data object Profile:NavRoutes("Profile")

    data object AddDisaster:NavRoutes("AddDisaster")

    data object Map:NavRoutes("Map")

    data object DisasterDetails:NavRoutes("DisasterDetails/{data}")

    data object GlobalEvents:NavRoutes("GlobalEvents")
    data object EmergencyContacts:NavRoutes("EmergencyContacts")
}


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {

    NavHost(navController = navController, startDestination = NavRoutes.Splash.route) {
        composable(NavRoutes.Splash.route) {
            SplashScreen(navController)
        }
        composable(NavRoutes.Home.route) {
            HomeScreen(navController)
        }
        composable(NavRoutes.Search.route) {
            val viewModel: EmergencyContactViewModel = hiltViewModel()
            val events = viewModel.datas.collectAsLazyPagingItems()
            EmergenyContactScreen(events)
        }
        composable(NavRoutes.Map.route) {
            MapScreen()
        }
        composable(NavRoutes.Profile.route) {
            ProfileScreen(navController)
        }
        composable(NavRoutes.AddDisaster.route){
            AddDisasterScreen(navController)
        }
        composable(NavRoutes.BottomNav.route) {
            BottomNavScreen(navController)
        }
        composable(NavRoutes.Login.route){
            LoginScreen(navController)
        }
        composable(NavRoutes.Register.route) {
            RegisterScreen(navController)
        }
        composable(NavRoutes.DisasterDetails.route) {
            val data = it.arguments!!.getString("data")
            DisasterDetails(navController,data!!)
        }

        composable(NavRoutes.GlobalEvents.route) {
            val viewModel: GlobalEventsViewModel = hiltViewModel()
            val events = viewModel.events.collectAsLazyPagingItems()
            GlobalEventsScreen(events) {

            }
        }


    }


}