package com.example.salttech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ui.screen.ProductScreen

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductScreen()
        }
    }
}