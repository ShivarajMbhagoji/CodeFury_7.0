package com.hackathon.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.hackathon.ui.components.DisasterItem
import com.hackathon.viewmodel.ProfileViewModel


@Composable
fun ProfileScreen(navController: NavHostController, modifier: Modifier = Modifier,profileViewModel: ProfileViewModel= viewModel()) {
    val firebaseUser by profileViewModel.users.collectAsState()
    val disasterList by profileViewModel.disasters.collectAsState()


    Firebase.auth.currentUser?.let { profileViewModel.fetchUser(it.uid) }
    Firebase.auth.currentUser?.let { profileViewModel.fetchDisasters(it.uid) }
    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Text(text = "Name : ${firebaseUser.name}", fontSize = 16.sp)
        }

        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "email : ${firebaseUser.email}", fontSize = 16.sp)
        }
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Disaster Reported : ${firebaseUser.disReported}", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            modifier = modifier.fillMaxWidth(.95f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(disasterList) {
                Spacer(modifier = Modifier.height(10.dp))
                DisasterItem(it,navController)
            }
        }
    }

}