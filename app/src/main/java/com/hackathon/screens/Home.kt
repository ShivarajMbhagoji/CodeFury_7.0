package com.hackathon.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import com.hackathon.ui.theme.backgroundColor
import com.hackathon.viewmodel.HomeViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier,homeViewModel: HomeViewModel = viewModel()) {

    var currentUserId = ""
    if (FirebaseAuth.getInstance().currentUser != null) {
        currentUserId = FirebaseAuth.getInstance().currentUser!!.uid
    }


    LazyColumn(modifier = modifier
        .fillMaxWidth()
        .background(backgroundColor), horizontalAlignment = Alignment.CenterHorizontally) {


    }
}