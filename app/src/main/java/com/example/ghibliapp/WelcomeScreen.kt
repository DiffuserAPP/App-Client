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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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



@Composable
fun WelcomeScreen(navController: NavController) {
    WelcomeFullScreenBackground()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("WELCOME",
            color = Color(0xFF00668B),
            fontSize = 32.sp,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 32.dp),

            )

        Text("Chose an input type",
            color = Color(0xFF00668B),
            fontSize = 20.sp,
            fontFamily = FontFamily.Default,
            modifier = Modifier.padding(top = 32.dp),

            )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate(Destinations.TEXT_INPUT_SCREEN) },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Text Prompt")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate(Destinations.IMG_INPUT_SCREEN)},
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Upload Image")
        }
    }
}
@Composable
fun WelcomeFullScreenBackground() {
    Image(
        painter = painterResource(R.drawable.landing_background),
        contentDescription = "background image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop // Adjusts the scaling of the image (Crop, Fit, etc.)
    )

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomeScreenPreview() {
    GhibliAppTheme {
        WelcomeFullScreenBackground()
        WelcomeScreen(rememberNavController())

    }
}