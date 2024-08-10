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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.hackathon.ui.components.DisasterItem
import com.hackathon.viewmodel.ProfileViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController, modifier: Modifier = Modifier,profileViewModel: ProfileViewModel= viewModel()) {
    val firebaseUser by profileViewModel.users.collectAsState()
    val disasterList by profileViewModel.disasters.collectAsState()


    Firebase.auth.currentUser?.let { profileViewModel.fetchUser(it.uid) }
    Firebase.auth.currentUser?.let { profileViewModel.fetchDisasters(it.uid) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Profile")
                },
                navigationIcon = {
                    Surface(
                        onClick = {},
                        color = Color.Transparent,
                    ) {
                        Row(modifier = Modifier.padding(vertical = 10.dp)) {
                            Icon(
                                Icons.Rounded.KeyboardArrowLeft, contentDescription = "Settings"
                            )
//                            Text("Report disaster")
                        }
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Row(modifier = Modifier.padding(8.dp)) {
                    Text(text = "Name : ${firebaseUser.name}", fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                        )
                }

                Row(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Email : ${firebaseUser.email}", fontSize = 16.sp,fontWeight = FontWeight.Bold)
                }
                Row(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Disaster Reported : ${disasterList.size}", fontSize = 16.sp,fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Disasters Reported",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            LazyColumn(
                modifier = modifier.fillMaxWidth(.95f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(disasterList) {
                    Spacer(modifier = Modifier.height(10.dp))
                    DisasterItem(it, navController)
                }
            }
        }
    }
}