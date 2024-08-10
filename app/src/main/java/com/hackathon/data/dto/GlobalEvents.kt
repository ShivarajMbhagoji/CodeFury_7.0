package com.hackathon.data.dto

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.hackathon.data.dto.Dimensions.MediumPadding1
import retrofit2.http.Url

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun GlobalEventsScreen(
    events: LazyPagingItems<Data>,
    navigateToDetails: (Url) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Global Disasters")
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
                .fillMaxSize().padding(innerPadding)
                .padding(top = MediumPadding1)
                .statusBarsPadding()
        ) {
            Spacer(modifier = Modifier.height(MediumPadding1))

            EventList(
                modifier = Modifier.padding(horizontal = MediumPadding1),
                events = events,
                onClick = {

                }
            )
        }
    }
}