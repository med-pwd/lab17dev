package com.example.receiverdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class CustomEventReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if ("com.example.receiverdemo.CUSTOM_EVENT" == intent.action) {
            val message = intent.getStringExtra("message")
            Toast.makeText(context, "Custom reçu : $message", Toast.LENGTH_LONG).show()
        }
    }
}
