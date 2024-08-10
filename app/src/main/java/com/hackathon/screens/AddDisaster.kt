package com.hackathon.screens

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.hackathon.model.DisasterModel
import com.hackathon.navigation.NavRoutes
import com.hackathon.ui.components.MyOutlinedTextField
import com.hackathon.ui.theme.txtHintColor
import com.hackathon.viewmodel.AddDisasterViewModel
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDisasterScreen(navController: NavHostController, modifier: Modifier = Modifier,
    vm:AddDisasterViewModel= viewModel()
) {
    var currentUserId = ""
    if (FirebaseAuth.getInstance().currentUser != null) {
        currentUserId = FirebaseAuth.getInstance().currentUser!!.uid
    }

    var selectedImages =  remember { mutableStateListOf<Uri?>() }
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetMultipleContents()){
        selectedImages = it.toMutableStateList()
    }
    val context = LocalContext.current
    val isPosted by vm.isPosted.collectAsState()

    val permissionToRequest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.READ_MEDIA_IMAGES
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }

    val permissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {

        }
    LaunchedEffect(isPosted) {
        if(isPosted)
        {
            Toast.makeText(context,"Posted",Toast.LENGTH_SHORT).show()
            navController.navigate(NavRoutes.Home.route) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
    }

    val state by vm.uiState.collectAsState()

    var disaster by remember { mutableStateOf(DisasterModel()) }

    val types= listOf(
        "Earthquake",
        "Wildfire",
        "Flood",
        "Heatwave",
        "Tsunami",
        "Cloud Burst",
        "Cyclone",
        "other",
    )

    val status= listOf(
        "OnGoing",
        "Stopped"
    )

    var showType by remember {
        mutableStateOf(false)
    }

    var showStatus by remember {
        mutableStateOf(false)
    }


    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Report")
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
                .padding(10.dp)
        ) {

            Spacer(modifier = Modifier.height(10.dp))


            Text(
                text = "Report a Disaster",
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold

            )

            Spacer(modifier = Modifier.height(20.dp))

            MyOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.place,
                label = "Place",
                onValueChange = {
                    vm.setPlace(it)
                },
                shape = RectangleShape,
                innerTextColor = txtHintColor,
                keyboardType = KeyboardType.Text
            )

            Spacer(modifier = Modifier.height(10.dp))


            Box(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .border(1.dp, Color.Gray),
                contentAlignment = Alignment.CenterStart,


                ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween

                ){
                    Text(
                        text = state.type,
                        modifier = Modifier
                            .padding(start = 20.dp)

                    )
                    IconButton(onClick = { showType=true }) {
                        Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
                        DropdownMenu(
                            expanded = showType,
                            onDismissRequest = {
                                showType = false
                            }
                        ) {
                            for (type in types){
                                DropdownMenuItem(text = { Text(type) },
                                    onClick = {
                                        vm.setType(type)
                                        showType=false
                                    }
                                )
                            }
                        }
                    }
                }
            }




            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .border(1.dp, Color.Gray),
                contentAlignment = Alignment.CenterStart,


                ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween

                ){
                    Text(
                        text = state.status,
                        modifier = Modifier
                            .padding(start = 20.dp)

                    )
                    IconButton(onClick = { showStatus=true }) {
                        Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
                        DropdownMenu(
                            expanded = showStatus,
                            onDismissRequest = {
                                showStatus = false
                            }
                        ) {
                            for (status in status){
                                DropdownMenuItem(text = { Text(status) },
                                    onClick = {
                                        vm.setStatus(status)
                                        showStatus=false
                                    }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))


            Box(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .border(1.dp, Color.Gray),
                contentAlignment = Alignment.CenterStart

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Add Images",
                        modifier = Modifier.padding(start = 20.dp)
                    )

                    Icon(
                        imageVector = Icons.Filled.AddCircle,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .clickable {
                                val isGranted = ContextCompat.checkSelfPermission(
                                    context, permissionToRequest
                                ) == PackageManager.PERMISSION_GRANTED

                                if (isGranted) {
                                    launcher.launch("image/*")
                                } else {
                                    permissionLauncher.launch(permissionToRequest)
                                }
                            }
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))


            Button(
                onClick = {
                    disaster=DisasterModel(
                        place = state.place,
                        title = state.type,
                        status = state.status,
                        uid = currentUserId,
                        disasterId = UUID.randomUUID().toString()
                    )
                    vm.saveData(disaster, selectedImages)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Report")
            }
        }
    }
}






