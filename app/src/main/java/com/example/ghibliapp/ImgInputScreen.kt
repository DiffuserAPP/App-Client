package com.example.ghibliapp

import android.content.Intent
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import android.Manifest
import android.graphics.Bitmap
import androidx.compose.ui.graphics.asImageBitmap


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ImgInputScreen(navController: NavController) {
    ImgInputFullScreenBackground()

    // Permission state
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    val storagePermissionState = rememberPermissionState(Manifest.permission.READ_EXTERNAL_STORAGE)

    // Remember an image URI or Bitmap
    var imageUri by remember { mutableStateOf<String?>(null) }
    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }

    val context = LocalContext.current

    // Gallery launcher
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        imageUri = uri.toString()
        imageBitmap = null // Reset the bitmap when an image is chosen from the gallery

    }

    // Camera launcher
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        imageBitmap = bitmap
        imageUri = null // Reset the URI when an image is captured by the camera
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Upload your Image",
            color = Color(0xFF00668B),
            fontSize = 32.sp,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 32.dp),

            )


        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick =
            {
                storagePermissionState.launchPermissionRequest()
                galleryLauncher.launch("image/*")
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Upload from Gallery")
        }
        Button(
            onClick =
            {
                cameraPermissionState.launchPermissionRequest()
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                cameraLauncher.launch(null)
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Take a Photo")
        }
        // Display the selected image
        imageUri?.let {
            // Display image from gallery
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = "Selected Image",
                modifier = Modifier.size(200.dp)
            )
        } ?: imageBitmap?.let { bitmap ->
            // Display image captured from camera
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Captured Image",
                modifier = Modifier.size(200.dp)
            )
        }
        if (imageUri != null || imageBitmap != null) {
            Button(
                onClick = { navController.navigate(Destinations.WELCOME_SCREEN) },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Generate Image")
            }
        }

    }
}

@Composable
fun ImgInputFullScreenBackground() {
    Image(
        painter = painterResource(R.drawable.landing_background),
        contentDescription = "background image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop // Adjusts the scaling of the image (Crop, Fit, etc.)
    )
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ImgInputScreenPreview() {
    GhibliAppTheme {
        ImgInputScreen(rememberNavController())
    }
}