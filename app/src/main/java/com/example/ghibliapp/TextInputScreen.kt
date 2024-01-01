package com.example.ghibliapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ghibliapp.ui.theme.GhibliAppTheme

import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle


@Composable
fun TextInputScreen(navController: NavController) {
    TextInputFullScreenBackground()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Introduce your prompt",
            color = Color(0xFF00668B),
            fontSize = 32.sp,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 32.dp),

            )

        Text("Example: Cat with mononoke mask",
            color = Color(0xFF00668B),
            fontSize = 20.sp,
            fontFamily = FontFamily.Default,
            modifier = Modifier.padding(top = 32.dp),

            )
        Spacer(modifier = Modifier.height(16.dp))

        var text by remember { mutableStateOf("") }

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter text") },
            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp)
        )
        Button(
            onClick = { navController.navigate(Destinations.WELCOME_SCREEN) },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Generate Image")
        }

    }
}
@Composable
fun TextInputFullScreenBackground() {
    Image(
        painter = painterResource(R.drawable.landing_background),
        contentDescription = "background image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop // Adjusts the scaling of the image (Crop, Fit, etc.)
    )
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TextInputScreenPreview() {
    GhibliAppTheme {
        TextInputFullScreenBackground()
        TextInputScreen(rememberNavController())
    }
}