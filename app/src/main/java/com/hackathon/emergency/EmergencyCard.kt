package com.hackathon.emergency

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackathon.R
import com.hackathon.ui.theme.CodeFury_70Theme


@Composable
fun ContactCard(
    modifier: Modifier = Modifier.background(Color.LightGray),
    data: Data,
    onClick: (() -> Unit)? = null
) {



        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .border(2.dp, Color.Cyan)
                .padding(horizontal = 10.dp)
                .height(96.dp)


        ) {

            Row(
//                modifier=Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {

                Text(
                    text = data.name,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    style = MaterialTheme.typography.bodyLarge.copy(),
                    color = colorResource(id = R.color.body)
                )

                Text(
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp),
                    textAlign = TextAlign.Right,
                    text = data.emergencyNumber?:"",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Red
                )

            }
            Text(
                text = "FireNumber: "+data.fireNumber,
                style = MaterialTheme.typography.bodyLarge.copy(),
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "PoliceNumber: "+data.policeNumber,
                style = MaterialTheme.typography.bodyLarge.copy(),
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

        }

}

@Preview
@Composable
fun previ() {
    CodeFury_70Theme {
        Surface {
            ContactCard(data = Data(
                "fghb",
                "nxn",
                "lug",
                234,
                "mnbn",
                ",mn b"

            )
            )
        }
    }
}