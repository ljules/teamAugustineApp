package com.example.teamaugustineapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel // Import après ajout dépendance
import com.example.teamaugustineapp.NavyBlue
import com.example.teamaugustineapp.ShellOrange
import com.example.teamaugustineapp.viewmodel.RaceViewModel



@Composable
fun PilotScreen(viewModel: RaceViewModel = viewModel()) {

    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NavyBlue)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. Barre supérieure
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoText(label = "🔄 ${state.lapProgress}")
            InfoText(label = "⏱️ ${state.timer}")
        }

        Spacer(modifier = Modifier.weight(1f))

        // 2. Vitesse Centrale
        SpeedDisplay(speed = state.speed)

        // 3. Zone d'Instruction et Drapeaux (Anciennement InstructionDisplay)
        Column(
            modifier = Modifier.padding(vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Ici on pourrait changer l'icône selon state.flag
            Text(text = if (state.flag == "NE PAS DOUBLER") "🏁🏁" else "🚩", fontSize = 40.sp)
            Text(
                text = state.flag.lowercase(),
                color = Color.Yellow,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // 4. Barre inférieure (Anciennement BottomActions)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "🏎️ ${state.instruction.lowercase()}",
                color = Color.Red,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = "↪️ sortir", color = Color.White, fontSize = 20.sp)
        }
    }
}

@Composable
fun SpeedDisplay(speed: Float) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = "%.1f".format(speed),
                fontSize = 100.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = " km/h",
                fontSize = 30.sp,
                color = ShellOrange,
                modifier = Modifier.padding(bottom = 20.dp)
            )
        }
        Text(text = "(vit. GPS)", color = Color.Gray, fontSize = 14.sp)
    }
}

@Composable
fun InfoText(label: String) {
    Text(
        text = label,
        color = ShellOrange,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold
    )
}