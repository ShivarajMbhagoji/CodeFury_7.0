package com.hackathon.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.hackathon.model.DisasterModel
import com.hackathon.navigation.NavRoutes


@Composable
fun DisasterItem(disasterModel: DisasterModel, navController: NavHostController, modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.height(10.dp))
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable {
                val routes = NavRoutes.DisasterDetails.route.replace("{data}", disasterModel.disasterId)
                navController.navigate(routes)
            },

        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.5f)

                    .padding(start = 5.dp),
                verticalArrangement = Arrangement.Center,

                ) {
                Text(
                    modifier = Modifier.padding(0.dp),
                    text = disasterModel.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    modifier = Modifier.padding(0.dp),
                    text = disasterModel.status,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp
                )
            }
            Text(
                text = disasterModel.place,
                modifier = Modifier.fillMaxWidth()
                ,
                textAlign = TextAlign.Right,
            )

        }
    }


}