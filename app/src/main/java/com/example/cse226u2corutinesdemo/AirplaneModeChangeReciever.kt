package com.example.cse226u2corutinesdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.TextView
import android.widget.Toast

class AirplaneModeChangeReciever(var tv :TextView):BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        val isAirPlaneModeEnabled=intent?.getBooleanExtra("state",false)?:return
        if(isAirPlaneModeEnabled){
            Toast.makeText(context,"Airplane mode Enabled",Toast.LENGTH_LONG).show()
            tv.text="Ariplane mode on"
        }else{
            Toast.makeText(context,"Airplane mode Disabled",Toast.LENGTH_LONG).show()
            tv.text="Airplane mode off"
        }
    }

}
