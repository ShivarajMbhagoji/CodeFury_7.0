package com.hackathon.emergency

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.hackathon.data.dto.Dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergenyContactScreen(
    datas: LazyPagingItems<Data>
) {

    var search by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ), title = {
                Text("Emergency Contacts")
            }, navigationIcon = {
                Surface(
                    onClick = {},
                    color = Color.Transparent,
                ) {
                    Row(modifier = Modifier.padding(vertical = 10.dp)) {
                        Icon(
                            Icons.AutoMirrored.Rounded.KeyboardArrowLeft, contentDescription = "Settings"
                        )
//                            Text("Report disaster")
                    }
                }
            })
        },
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {


            OutlinedTextField(value = search,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding).padding(10.dp),
                onValueChange = { search = it },
                label = { Text("Search by Country") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") })
            Spacer(modifier = Modifier.height(Dimensions.MediumPadding1))
            EmergencyList(
                modifier = Modifier.padding(horizontal = Dimensions.MediumPadding1), datas = datas
            )
        }
    }
}