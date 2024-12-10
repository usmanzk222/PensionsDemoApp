package com.usman.pensionsdemo.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SWButton(
    onClick: () -> Unit,
    buttonText: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            contentColor = Color.White
        ),
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = buttonText,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomButton() {
    SWButton(
        onClick = {},
        buttonText = "Do you have lost pensions",
        modifier = Modifier
    )
}
