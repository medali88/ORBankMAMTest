package com.mma.orbankmamtest.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = OperationsOrange,
    primaryVariant = OperationsOrangeDark,
    secondary = OperationsLight,
    secondaryVariant = OperationsDark,
    background = OperationsOrangeLight,
    surface = OperationsOrangeLight,
    onPrimary = OperationsOrange,
    onSecondary = OperationsOrange,
    onBackground = OperationsOrange,
    onSurface = OperationsOrange,
    onError = OperationsRedLight,
    error = OperationsRed
)

@Composable
fun OperationsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes
    ) {
        content()
    }
}
