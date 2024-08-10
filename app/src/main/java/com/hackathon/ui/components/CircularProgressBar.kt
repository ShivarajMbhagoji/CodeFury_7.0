package com.hackathon.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CircularProgress(progressPercentage: Float) {
    var animationTriggered by remember {
        mutableStateOf(false)
    }
    val percentage by animateFloatAsState(
        targetValue = if (animationTriggered) if (progressPercentage.isNaN()) 0f else progressPercentage else 0f,
        animationSpec = tween(
            durationMillis = 500,
            delayMillis = 50
        ), label = "anime"
    )
    LaunchedEffect(key1 = true) {
        delay(500)
        animationTriggered = true
    }
    Canvas(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
    ) {
        drawArc(
            size = Size(size.width, size.height),
            startAngle = 140f,
            sweepAngle = 260f,
            useCenter = false,
            style = Stroke(width = 10.dp.toPx(), cap = StrokeCap.Round),
            color = Color.LightGray
        )
        drawArc(
            size = Size(size.width, size.height),
            startAngle = 140f,
            sweepAngle = percentage * 260f,
            useCenter = false,
            style = Stroke(width = 20.dp.toPx(), cap = StrokeCap.Round),
            color = Color.Blue
        )
        val angleInDegrees = (percentage * 260.0) + 50.0
        val radius = (size.height / 2)
        val x = -(radius * sin(Math.toRadians(angleInDegrees))).toFloat() + (size.width / 2)
        val y = (radius * cos(Math.toRadians(angleInDegrees))).toFloat() + (size.height / 2)
        drawCircle(
            color = Color.Blue,
            radius = 35f,
            center = Offset(x, y)
        )
        drawCircle(
            color = Color.White,
            radius = 15f,
            center = Offset(x, y)
        )
    }
}