package com.example.cse226u2corutinesdemo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MusicService : AppCompatActivity() {
    private lateinit var start:Button
    private lateinit var stop:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_service)
        start=findViewById(R.id.start)
        stop=findViewById(R.id.stop)
        start.setOnClickListener {
            startService(Intent(this,MusicServiceDemo::class.java))
        }
        stop.setOnClickListener {
            stopService(Intent(this,MusicServiceDemo::class.java))
        }
    }
}