package com.example.cse226u2corutinesdemo

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class BatteryActivityExample : AppCompatActivity() {
    lateinit var textView1: TextView
    lateinit var br:BatteryReciever
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battery_example)
        textView1=findViewById(R.id.text1)
        br= BatteryReciever(textView1,this)
        registerReceiver(br, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(br)
    }
}