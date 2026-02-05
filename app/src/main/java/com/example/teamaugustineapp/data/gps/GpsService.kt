package com.example.teamaugustineapp.data.gps

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


class GpsService(context: Context) {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission") // La vérification sera faite dans l'UI/Activity
    fun getSpeedUpdates(): Flow<Float> = callbackFlow {

        // Configuration de la requête GPS :
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY, 1000 // Update toutes les 1000ms (1s)
        ).build()

        // Le callback qui reçoit les données du capteur
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    // speed est en m/s par défaut, conversion en km/h
                    val speedKmH = location.speed * 3.6f
                    trySend(speedKmH)
                }
            }
        }

        // Lancement de l'écoute
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )

        // Fermeture du flux si on n'en a plus besoin (nettoyage mémoire)
        awaitClose {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }
}