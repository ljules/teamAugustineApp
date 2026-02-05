package com.example.teamaugustineapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.teamaugustineapp.data.gps.GpsService
import com.example.teamaugustineapp.data.timer.TimeService
import com.example.teamaugustineapp.model.PilotUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RaceViewModel(application: Application) : AndroidViewModel(application) {

    private val gpsService = GpsService(application)
    private val _uiState = MutableStateFlow(PilotUiState())
    val uiState: StateFlow<PilotUiState> = _uiState.asStateFlow()

    private val timeService = TimeService()
    private var isTimerRunning = false

    init {
        startGpsTracking()
    }

    private fun startGpsTracking() {
        viewModelScope.launch {
            gpsService.getSpeedUpdates().collect { newSpeed ->
                _uiState.value = _uiState.value.copy(speed = newSpeed)

                if (!isTimerRunning && newSpeed > 8.0f) {
                    startRaceTimer()
                }
            }
        }
    }

    private fun startRaceTimer() {
        isTimerRunning = true
        viewModelScope.launch {
            timeService.timerFlow().collect { seconds ->
                _uiState.value = _uiState.value.copy(
                    timer = timeService.formatSeconds(seconds)
                )
            }
        }
    }
} // Fin de la classe RaceViewModel