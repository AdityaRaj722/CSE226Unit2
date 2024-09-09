package com.example.cse226u2corutinesdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.widget.TextView
import android.widget.Toast

class BatteryReciever(var  tv:TextView,val context:Context):BroadcastReceiver (){
    override fun onReceive(context: Context?, intent: Intent?) {
        val prec= intent?.getIntExtra("level",0)
        if(prec!=0){
            tv.text="$prec%"
        }
        val batteryStatus= intent?.getIntExtra(BatteryManager.EXTRA_STATUS,0)
        val ischnaging:Boolean=batteryStatus==BatteryManager.BATTERY_STATUS_CHARGING
                ||batteryStatus==BatteryManager.BATTERY_STATUS_FULL
        if(ischnaging){
            Toast.makeText(context,"Charger connected",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context,"Charger Disconnected",Toast.LENGTH_LONG).show()
        }

    }
}