package com.example.receiverdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneModeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // Cette méthode est appelée À CHAQUE fois qu'un broadcast correspondant arrive
        if (Intent.ACTION_AIRPLANE_MODE_CHANGED == intent.action) {
            
            // Récupère l'état du mode avion (true = activé, false = désactivé)
            val isAirplaneOn = intent.getBooleanExtra("state", false)
            
            val message = if (isAirplaneOn) 
                "Mode Avion ACTIVÉ - Plus de connexion !" 
            else 
                "Mode Avion DÉSACTIVÉ - Connexions rétablies"
            
            // Toast pour voir le résultat immédiatement
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}
