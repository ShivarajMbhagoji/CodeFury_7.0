package com.hackathon.data.dto

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hackathon.R
import com.hackathon.data.dto.Dimensions.ExtraSmallPadding
import com.hackathon.data.dto.Dimensions.SmallIconSize
import com.hackathon.ui.theme.CodeFury_70Theme

@Composable
fun EventCard(
    modifier: Modifier = Modifier.background(Color.LightGray),
    event: Data,
    onClick: (() -> Unit)? = null
) {

    val context = LocalContext.current
    Row(
        modifier = modifier.clickable {
            Intent(Intent.ACTION_VIEW).also {
                it.data = Uri.parse(event.url)
                if (it.resolveActivity(context.packageManager) != null) {
                    context.startActivity(it)
                }
            }
        },

        ) {

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .height(96.dp)
        ) {
            Text(
                text = event.title,
                style = MaterialTheme.typography.bodyMedium.copy(),
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = event.description,
                style = MaterialTheme.typography.bodySmall.copy(),
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    modifier = Modifier.size(SmallIconSize),
                    tint = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.width(ExtraSmallPadding))
                Text(
                    text = event.updatedAt,
                    style = MaterialTheme.typography.labelSmall,
                    color = colorResource(id = R.color.body)
                )


//                Text(
//                    modifier=Modifier.fillMaxWidth()
//                        .padding(end=10.dp),
////                    textAlign = TextAlign.Right,
//                    text = "alertlevel",
//                    style = MaterialTheme.typography.bodyLarge,
//                    color = Color.Red
//                )

                Text(
                    modifier=Modifier.fillMaxWidth()
                        .padding(end=10.dp),
                    textAlign = TextAlign.Right,
                    text = "AlertLevel:"+event.alertlevel,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Red
                )

            }
        }
    }
}


@Preview
@Composable
fun previ() {
    CodeFury_70Theme {
        Surface {
            EventCard(event = Data(
                id="c1faa204-7473-4ae4-9312-7f29a0cc6797",
                title= "Green forest fire alert in Canada",
            description ="On 05/08/2024, a forest fire started in Canada,  until 10/08/2024.",
            url= "",
            severity= "Green impact for forestfire in 5631 ha",
            alertlevel= "Green",
            alertscore= 1,
            createdAt ="2024-08-10T15:06:41.000Z",
            magnitudeUnit ="ha",
            magnitudeValue= "5631",
            type="WF",
            guid= "WF1021020",
            updatedAt= "2024-08-10T15:59:00.572Z",
            _deletedAt= "",
            landCountry= "Canada",
            latlong= listOf(
            60.41954566035352,
            -117.33949781342874
            )

            ))
        }
    }
}
