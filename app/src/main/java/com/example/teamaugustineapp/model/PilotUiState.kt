package com.example.teamaugustineapp.model

import androidx.compose.ui.graphics.Color


data class PilotUiState(
    val speed: Float = 0.0f,
    val lapProgress: String = "1/11",
    val timer: String = "00:00",
    val instruction: String = "MAINTENIR",
    val flag: String = "COURSE",
    val zoneColor: Color = Color.Transparent
) {
}