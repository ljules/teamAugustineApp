package com.example.teamaugustineapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.ui.graphics.Color
import com.example.teamaugustineapp.ui.screens.PilotScreen


// Définition des couleurs de votre charte graphique
val NavyBlue = Color(0xFF00334D) // Le fond sombre de votre maquette
val ShellOrange = Color(0xFFFF8C00) // L'orange pour les unités

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // L'écran principal
            PilotScreen()
        }
    }
}

