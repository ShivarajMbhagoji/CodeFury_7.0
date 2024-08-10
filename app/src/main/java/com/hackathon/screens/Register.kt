package com.hackathon.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.hackathon.navigation.NavRoutes
import com.hackathon.ui.components.MyOutlinedTextField
import com.hackathon.ui.theme.backgroundColor
import com.hackathon.ui.theme.txtColor
import com.hackathon.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    val context = LocalContext.current

    val firebaseUser by authViewModel.firebaseUser.collectAsState()

    LaunchedEffect(firebaseUser) {
        if (firebaseUser != null) {
            navController.navigate(NavRoutes.BottomNav.route) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
    }

    Box(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(modifier = Modifier.padding(top = 5.dp),text = "Sign Up", fontWeight = FontWeight.SemiBold, fontSize = 28.sp,color=txtColor)

            MyOutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = "Name",
                modifier = modifier.fillMaxWidth(),
                innerTextColor = txtColor,
                shape = RoundedCornerShape(14.dp),
                keyboardType = KeyboardType.Text,
            )

            MyOutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email",
                modifier = modifier.fillMaxWidth(),
                innerTextColor = txtColor,
                shape = RoundedCornerShape(14.dp),
                keyboardType = KeyboardType.Email,
            )
            MyOutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                modifier = modifier.fillMaxWidth(),
                innerTextColor = txtColor,
                shape = RoundedCornerShape(14.dp),
                keyboardType = KeyboardType.Password,
            )
            Spacer(modifier = Modifier.height(5.dp))
            ElevatedButton(
                onClick = {

                    if (name.isEmpty() || password.isEmpty() || email.isEmpty()) {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    } else {
                        authViewModel.register(
                            email, password, name, context
                        )
                    }

                },
                colors = ButtonDefaults.buttonColors()
                    .copy(containerColor = Color(0xFFDA5D5D), contentColor = Color(0xFFFFFFFF))
            ) {
                Text(
                    text = "Sign Up",
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp, color =Color(0xFFFCF2F2)
                )
            }
            TextButton(onClick = {
                navController.navigate(NavRoutes.Login.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }) {
                Text(text = "Already have an Account? Login Here",color = Color(0xFF0D83FF))
            }
        }
    }
}
