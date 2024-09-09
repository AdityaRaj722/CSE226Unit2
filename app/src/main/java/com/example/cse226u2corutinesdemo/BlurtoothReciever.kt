package com.example.cse226u2corutinesdemo

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BlurtoothReciever:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val state= intent?.getIntExtra(BluetoothAdapter.EXTRA_STATE,BluetoothAdapter.ERROR)
        val message=when(state){
            BluetoothAdapter.STATE_OFF->"Bluetooth is off"
            BluetoothAdapter.STATE_ON->"Bluetooth is ON"
            BluetoothAdapter.STATE_TURNING_OFF->"Bluetooth is turning off"
            BluetoothAdapter.STATE_TURNING_ON->"Bluetooth is turniong on"
            else->"Bluetooth state unkown"
        }
        if(context is BluetoothModeChange){
            context.updateBluetoothState(message)
        }
    }

}