package com.example.cse226u2corutinesdemo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class Corutine_Demo2 : AppCompatActivity() {
    private lateinit var imageview:ImageView
    private val imageUrl = "https://img.freepik.com/free-vector/cute-happy-smiling-child-isolated-white_1308-32243.jpg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_corutine_demo2)
        imageview=findViewById(R.id.imagewithoutapi)
        lifecycleScope.launch(Dispatchers.IO) {
            val imageData= fetchImage(imageUrl)
            if (imageData!=null){
                val bitmap=BitmapFactory.decodeByteArray(imageData,0,imageData.size)
                withContext(Dispatchers.Main){
                    imageview.setImageBitmap(bitmap)
                }
            }
        }
    }

    private suspend fun fetchImage(url: String): ByteArray? {
    return withContext(Dispatchers.IO){
        val client=OkHttpClient()
        val request=Request.Builder().url(url).build()
        val response=client.newCall(request).execute()
        if (response.isSuccessful){
            response.body?.bytes()
        }else{
            null
        }
    }
    }
}