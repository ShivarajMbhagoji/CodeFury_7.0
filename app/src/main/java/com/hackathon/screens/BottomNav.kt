package com.hackathon.screens


import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.hackathon.data.dto.GlobalEventsScreen
import com.hackathon.data.dto.GlobalEventsViewModel
import com.hackathon.emergency.EmergencyContactViewModel
import com.hackathon.emergency.EmergenyContactScreen
import com.hackathon.model.NavBarItem
import com.hackathon.navigation.NavRoutes

@Composable
fun BottomNavScreen(navHostController: NavHostController, modifier: Modifier = Modifier) {
    val navController1 = rememberNavController()

    Scaffold(bottomBar = {
        MyBottomBar(navController1)
    }) { innerPadding ->
        NavHost(
            navController = navController1,
            modifier = Modifier.padding(innerPadding),
            startDestination = NavRoutes.Home.route
        ) {
            composable(NavRoutes.Home.route) {
                HomeScreen(navHostController)
            }

            composable(NavRoutes.Search.route) {
                val viewModel: EmergencyContactViewModel = hiltViewModel()
                val events = viewModel.datas.collectAsLazyPagingItems()
                EmergenyContactScreen(events)
            }

            composable(NavRoutes.GlobalEvents.route) {
                val viewModel: GlobalEventsViewModel = hiltViewModel()
                val events = viewModel.events.collectAsLazyPagingItems()
                GlobalEventsScreen(events) {

                }
            }
            composable(NavRoutes.Profile.route) {
                ProfileScreen(navHostController)
            }
            composable(NavRoutes.AddDisaster.route) {
                AddDisasterScreen(navController1)
            }
        }
    }
}

@Composable
fun MyBottomBar(navController1: NavHostController) {
    val backStackEntry = navController1.currentBackStackEntryAsState()
    val listOfNavBarItems = listOf(
        NavBarItem(
            "Home", NavRoutes.Home.route, Icons.Rounded.Home
        ), NavBarItem(
            "Search", NavRoutes.Search.route, Icons.Filled.Call
        ), NavBarItem(
            "Add Disaster", NavRoutes.AddDisaster.route, Icons.Filled.Add
        ), NavBarItem(
            "GlobalEvents", NavRoutes.GlobalEvents.route, Icons.Filled.MailOutline
        ), NavBarItem(
            "Profile", NavRoutes.Profile.route, Icons.Rounded.Person
        )
    )
    BottomAppBar {
        listOfNavBarItems.forEach {
            val selected = it.route == backStackEntry.value?.destination?.route

            NavigationBarItem(selected = selected, onClick = {
                navController1.navigate(it.route) {
                    popUpTo(navController1.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                }
            }, icon = { Icon(imageVector = it.icon, contentDescription = it.title) })
        }
    }
}