package com.mma.orbankmamtest.presentation.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Light,
        fontSize = 35.sp,
        lineHeight = 44.sp,
        color = OperationsOrangeDark
    ),
    h2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 35.sp,
        lineHeight = 44.sp,
        color = OperationsOrangeDark
    ),
    h3 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 21.sp,
        lineHeight = 26.sp,
        color = OperationsOrangeDark
    ),
    h4 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp,
        lineHeight = 24.sp,
        color = OperationsOrangeDark
    ),
    h5 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 22.sp,
        color = OperationsOrangeDark
    ),
    h6 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        lineHeight = 16.sp,
        color = OperationsOrangeDark
    ),
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        lineHeight = 24.sp,
        color = OperationsOrangeDark
    ),
    body2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        color = OperationsOrangeDark
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 16.sp,
        color = OperationsOrangeDark
    )
)

@Composable
fun Typography.xxsLargeHeading() = h1

@Composable
fun Typography.xxLargeHeading() = h2

@Composable
fun Typography.xLargeHeading() = h3

@Composable
fun Typography.largeHeading() = h4

@Composable
fun Typography.defaultHeading() = h5

@Composable
fun Typography.smallHeading() = h6

@Composable
fun Typography.largeText() = body1

@Composable
fun Typography.defaultText() = body2

@Composable
fun Typography.smallText() = caption

@Preview(showBackground = true)
@Composable
fun TypographyHeadingPreview() {
    OperationsTheme {
        Column(modifier = Modifier.padding(4.dp)) {
            Text(text = "Heading: large xxs", style = MaterialTheme.typography.xxsLargeHeading())
            Text(text = "Heading: large xx", style = MaterialTheme.typography.xxLargeHeading())
            Text(text = "Heading: large x", style = MaterialTheme.typography.xLargeHeading())
            Text(text = "Heading: large", style = MaterialTheme.typography.largeHeading())
            Text(text = "Heading: default", style = MaterialTheme.typography.defaultHeading())
            Text(text = "Heading: small", style = MaterialTheme.typography.smallHeading())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TypographyTextPreview() {
    OperationsTheme {
        Column(modifier = Modifier.padding(4.dp)) {
            Text(text = "Text: large", style = MaterialTheme.typography.largeText())
            Text(text = "Text: default", style = MaterialTheme.typography.defaultText())
            Text(text = "Text: small", style = MaterialTheme.typography.smallText())
        }
    }
}
