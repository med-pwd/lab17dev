package com.example.receiverdemo

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var airplaneReceiver: AirplaneModeReceiver
    private var isReceiverRegistered = false
    private lateinit var btnToggleAirplane: Button
    private lateinit var btnSendCustom: Button
    private lateinit var tvStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        airplaneReceiver = AirplaneModeReceiver()
        tvStatus = findViewById(R.id.tvStatus)
        btnToggleAirplane = findViewById(R.id.btnToggleAirplane)
        btnSendCustom = findViewById(R.id.btnSendCustom)

        btnToggleAirplane.setOnClickListener { toggleAirplaneReceiver() }
        btnSendCustom.setOnClickListener { sendCustomBroadcast() }
    }

    private fun toggleAirplaneReceiver() {
        if (!isReceiverRegistered) {
            // Création du filtre pour le receiver dynamique
            val filter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            
            // Enregistrement dynamique (recommandé pour les versions récentes)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                registerReceiver(airplaneReceiver, filter, Context.RECEIVER_EXPORTED)
            } else {
                registerReceiver(airplaneReceiver, filter)
            }
            
            isReceiverRegistered = true
            tvStatus.text = "Receiver Mode Avion : ACTIVÉ (dynamique)"
            btnToggleAirplane.text = "Désactiver Receiver Avion"
        } else {
            unregisterReceiver(airplaneReceiver)
            isReceiverRegistered = false
            tvStatus.text = "Receiver Mode Avion : DÉSACTIVÉ"
            btnToggleAirplane.text = "Activer Receiver Avion"
        }
    }

    private fun sendCustomBroadcast() {
        val intent = Intent("com.example.receiverdemo.CUSTOM_EVENT")
        intent.setPackage(packageName) // Recommandé pour les broadcasts internes
        intent.putExtra("message", "Bonjour depuis le custom broadcast !")
        sendBroadcast(intent)
        
        Toast.makeText(this, "Custom Broadcast envoyé !", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        if (isReceiverRegistered) {
            unregisterReceiver(airplaneReceiver)
        }
        super.onDestroy()
    }
}
