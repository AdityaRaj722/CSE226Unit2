package com.example.cse226u2corutinesdemo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat

class ServiceDemo : AppCompatActivity() {
    lateinit var btnStart:Button
    lateinit var btnStop:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_demo)
        btnStart=findViewById(R.id.btnstart)
        btnStop=findViewById(R.id.btnStop)
        btnStart.setOnClickListener {
            val startIntent=Intent(this,k::class.java)
            startIntent.putExtra("inputExtra","Foreground Service is running...")
            ContextCompat.startForegroundService(this,startIntent)
        }
        btnStop.setOnClickListener {
            val stopIntent=Intent(this,k::class.java)
            stopService(stopIntent)
        }
    }
}