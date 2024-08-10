package com.hackathon.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.hackathon.ui.theme.backgroundColor

@Composable
fun DisasterDetails(navController: NavHostController,uId:String, modifier: Modifier = Modifier) {

    LazyColumn(modifier = modifier.fillMaxSize().background(backgroundColor), horizontalAlignment = Alignment.CenterHorizontally) {


    }

}