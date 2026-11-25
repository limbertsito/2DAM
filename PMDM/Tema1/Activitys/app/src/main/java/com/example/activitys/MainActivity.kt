package com.example.activitys

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.activitys.ui.theme.ActivitysTheme
private const val TAG="Estado"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Estado", "Create!!!")
        enableEdgeToEdge()
        setContent {
            ActivitysTheme {

                cambiarTexto()

            }
        }
    }
    override fun onStart(){
        super.onStart()
        Log.d("Estado", "Start")
    }
    override fun onResume(){
        super.onResume()
        Log.d("Estado", "Resume")
    }
    override fun onPause(){
        super.onPause()
        Log.d("Estado", "Pause")
    }
    override fun onStop(){
        super.onStop()
        Log.d("Estado", "Stop")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.d("Estado", "Destroy")
    }
}

@Composable
fun cambiarTexto(){
    var numero by rememberSaveable() { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
            Text(
                text = numero.toString(),
                fontSize = 65.sp,
                modifier = Modifier.clickable {
                    numero++
                }
            )
    }
}
