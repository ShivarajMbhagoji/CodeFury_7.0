package com.hackathon.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.hackathon.ui.components.CircularProgress
import com.hackathon.ui.components.DisasterItem
import com.hackathon.ui.theme.backgroundColor
import com.hackathon.viewmodel.HomeViewModel

@Composable
fun HomeScreen(navController: NavHostController, modifier: Modifier = Modifier, homeViewModel: HomeViewModel = viewModel()) {

    val disasterList by homeViewModel.disasters.collectAsState()

    var search by remember { mutableStateOf("") }




    Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            modifier = Modifier.padding(bottom = 5.dp),
            text = "Total Disasters",
            fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(15.dp))
        Box(contentAlignment = Alignment.Center) {
            CircularProgress(progressPercentage = disasterList.size / 30f)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    modifier = Modifier.padding(bottom = 5.dp),
                    text = "${disasterList.size}",
                    fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, fontSize = 20.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Column(modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = search,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                onValueChange = { search = it },
                label = { Text("Search by Place") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") }
            )
            LazyColumn(
                modifier = modifier.fillMaxWidth(.95f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val filterItems = disasterList.filter { it.place.contains(search,ignoreCase = true) }
                items(filterItems) {
                    Spacer(modifier = Modifier.height(10.dp))
                    DisasterItem(it,navController)
                }
            }

        }

    }

}