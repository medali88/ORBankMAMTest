package com.mma.orbankmamtest.presentation.theme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val OperationsOrange = Color(0xFFF7653B)
val OperationsOrangeDark = Color(0xFFD74D26)
val OperationsOrangeLight = Color(0xFFFEE2D5)

val OperationsDark = Color(0xFF333333)
val OperationsLight = Color(0xFF757575)
val OperationsXLight = Color(0xFFAFAFAF)

val OperationsWhite = Color(0xFFFFFFFF)
val OperationsLightGray = Color(0xFFFAFAFA)
val OperationsRed = Color(0xFFDE0000)

@Preview(showBackground = true, backgroundColor = 0xFFE0E0E0, widthDp = 300)
@Composable
fun ColorsPreview() {

    OperationsTheme {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Surface(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(top = 10.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Column(Modifier.padding(10.dp)) {
                    ColorPreview(padding = 0.dp, text = "Orange", color = OperationsOrange)
                    ColorPreview(text = "Orange Dark", color = OperationsOrangeDark)
                    ColorPreview(text = "Orange Light", color = OperationsOrangeLight)
                }
            }
            Surface(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(top = 10.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Column(Modifier.padding(10.dp)) {
                    ColorPreview(padding = 0.dp, text = "Dark", color = OperationsDark)
                    ColorPreview(text = "Light", color = OperationsLight)
                    ColorPreview(text = "X Light", color = OperationsXLight)
                }
            }
            Surface(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(top = 10.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Column(Modifier.padding(10.dp)) {
                    ColorPreview(padding = 0.dp, text = "White", color = OperationsWhite)
                    ColorPreview(text = "Light Gray", color = OperationsLightGray)
                    ColorPreview(text = "Red", color = OperationsRed)
                }
            }
        }
    }
}

@Composable
private fun ColorPreview(
    padding: Dp = 4.dp,
    text: String,
    color: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = padding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = text,
            style = MaterialTheme.typography.defaultText()
        )
        Canvas(modifier = Modifier.size(24.dp)) {
            drawArc(
                color = color,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false
            )
        }
    }
}
