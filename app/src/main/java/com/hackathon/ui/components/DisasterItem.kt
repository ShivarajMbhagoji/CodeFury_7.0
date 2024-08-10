package com.hackathon.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.hackathon.model.DisasterModel
import com.hackathon.navigation.NavRoutes
import com.hackathon.ui.theme.CodeFury_70Theme
import com.hackathon.ui.theme.rowBgColor


@Composable
fun DisasterItem(disasterModel: DisasterModel, navController: NavHostController, modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.height(10.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(rowBgColor)
            .height(80.dp)
            .clickable {
                val routes =
                    NavRoutes.DisasterDetails.route.replace("{data}", disasterModel.disasterId)
                navController.navigate(routes)
            },


    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart)
                .padding(5.dp)
                .padding(end=5.dp),

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
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
                    text = "Status : "+disasterModel.status,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp
                )
            }
            Row(
                modifier = Modifier

            ){
                Icon(
                    imageVector = Icons.Filled.LocationOn, contentDescription = ""
                )
                Text(
                    text = disasterModel.place,
                    textAlign = TextAlign.Right,
                )
            }

        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun create() {
    CodeFury_70Theme {
//        DisasterItem(disasterModel = , navController = )
    }
}