package com.example.ghibliapp

import API.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

fun SendText(navController: NavController,  text : String, )
{
    val request = RequestPostText(user_id = 42, text = text)

    val retrofit =  Retrofit.Builder().
    baseUrl("http://10.0.2.2:8000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    val serverapi : ServerAPI = retrofit.create(ServerAPI::class.java)
    GlobalScope.launch(Dispatchers.IO){
        val test = serverapi.sendTextServer(request)
        test.enqueue(object: Callback<ResponsePostText>
        {
            override fun onResponse(
                call: Call<ResponsePostText>,
                response: Response<ResponsePostText>
            ) {
                val tt = response.body()
                if (tt != null) {
                }
            }

            override fun onFailure(call: Call<ResponsePostText>, t: Throwable) {
                print(t.message)
            }
        })
    }
}


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
            onClick = {SendText(navController, text)  },
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