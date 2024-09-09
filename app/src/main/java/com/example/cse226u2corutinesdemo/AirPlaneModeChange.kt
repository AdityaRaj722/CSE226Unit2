package com.example.cse226u2corutinesdemo

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AirPlaneModeChange : AppCompatActivity() {
     lateinit var reciver:AirplaneModeChangeReciever
     lateinit var tv:TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_reciver)
        tv=findViewById(R.id.text)
         reciver= AirplaneModeChangeReciever(tv)
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(reciver,it)
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(reciver)
    }
}