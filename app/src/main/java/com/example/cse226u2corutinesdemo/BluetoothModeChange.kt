package com.example.cse226u2corutinesdemo

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class BluetoothModeChange : AppCompatActivity() {
    private val receiver= BlurtoothReciever()
    private  lateinit var textView:TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth_mode_change)

          textView=findViewById(R.id.textBB)
    }

    override fun onResume() {
        super.onResume()
        val filter=IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
        registerReceiver(receiver,filter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }
    fun updateBluetoothState(message: String) {
        textView.text=message
        // Update UI or perform any action with the Bluetooth state message
    }

}
