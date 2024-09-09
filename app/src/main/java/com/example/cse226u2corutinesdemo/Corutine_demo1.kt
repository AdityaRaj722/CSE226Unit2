package com.example.cse226u2corutinesdemo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import java.io.InputStream

class Corutine_demo1 : AppCompatActivity() {
    lateinit var imageView:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView=findViewById(R.id.imageview)
        val imageUrl = "https://www.cleverfiles.com/howto/wp-content/uploads/2018/03/minion.jpg"
        downLoadAndShowImage(imageUrl)
    }

    private fun downLoadAndShowImage(url: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response=RetrofitClient.apiService.downloadImage(url)
                if(response.isSuccessful){
                    val responseBody:ResponseBody=response.body()!!
                    val inputStream:InputStream=responseBody.byteStream()
                    val bitmap:Bitmap=BitmapFactory.decodeStream(inputStream)
                    withContext(Dispatchers.Main){
                        imageView.setImageBitmap(bitmap)
                    }
                }else{
                    println("Failed to download image:${response.message()}")
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

    }
}