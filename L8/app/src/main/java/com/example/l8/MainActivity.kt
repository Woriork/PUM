package com.example.l8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.l8.ui.theme.L8Theme
import com.example.l8.ui.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            L8Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding -> Navigation(Modifier.padding(innerPadding)) }
                )
            }
        }
    }
}
