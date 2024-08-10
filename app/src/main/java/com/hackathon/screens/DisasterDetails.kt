package com.hackathon.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.hackathon.viewmodel.DisasterDetailsViewModel


@Composable
fun DisasterDetails(
    navController: NavHostController,
    uId: String,
    modifier: Modifier = Modifier,
    disasterDetailsViewModel: DisasterDetailsViewModel = viewModel()
) {


    val disaster by disasterDetailsViewModel.disasters.collectAsState()

    var imgIndex by remember {
        mutableIntStateOf(0)
    }
    LaunchedEffect(key1 = true) {
        disasterDetailsViewModel.fetchDisaster(uId)
    }


    Scaffold(topBar = {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF91C7DF))
        ) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back"
                )
            }
            Text(
                text = "Disaster Description", fontSize = 20.sp
            )
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if(disaster.imgList.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = {
                        imgIndex = (imgIndex - 1 + disaster.imgList.size) % disaster.imgList.size
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Previous"
                        )
                    }
                    Image(
                        painter = rememberAsyncImagePainter(model = disaster.imgList[imgIndex]),
                        contentDescription = "dp",
                        modifier = Modifier
                            .padding(5.dp)
                            .size(200.dp),
                        contentScale = ContentScale.Crop
                    )
                    IconButton(onClick = {
                        imgIndex = (imgIndex + 1) % disaster.imgList.size
                    }) {
                        Icon(
                            imageVector =
                            Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "Next"
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Information About the \n Reported disaster",
                    fontSize = 30.sp,
                    lineHeight = 40.sp,
                    color = Color.Blue,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Disaster Type : ${disaster.title} \n\nLocation : ${disaster.place} \n\nCurrent Status : ${disaster.status} \n ",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
                )
            }
        }
    }
}

