package com.mma.orbankmamtest.presentation.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

@Preview(showBackground = true)
@Composable
fun ShapesPreview() {
    OperationsTheme {
        Column(
            modifier = Modifier.padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                shape = MaterialTheme.shapes.small,
                color = OperationsOrange
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Small shape"
                )
            }
            Surface(
                modifier = Modifier.padding(top = 4.dp),
                shape = MaterialTheme.shapes.medium,
                color = OperationsOrangeDark
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Medium shape"
                )
            }
            Surface(
                modifier = Modifier.padding(top = 4.dp),
                shape = MaterialTheme.shapes.large,
                color = OperationsLight
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Large shape"
                )
            }
        }
    }
}
