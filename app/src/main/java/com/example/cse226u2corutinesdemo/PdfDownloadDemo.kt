package com.example.cse226u2corutinesdemo

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.graphics.pdf.PdfRenderer.Page
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream

class PdfDownloadDemo : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private val pdfUrl = "https://www.iitk.ac.in/esc101/2009Jan/lecturenotes/timecomplexity/TimeComplexity_using_Big_O.pdf"
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_download_demo)
        textView=findViewById(R.id.textview)
        imageView=findViewById(R.id.imageview)
        lifecycleScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main){
                textView.text="Downloading PDF......"
            }
            val pdfFile=downloadPdf(pdfUrl)
            if(pdfFile!=null){
                withContext(Dispatchers.Main){
                    textView.text="PDF downloaded! Rendering the First page..."
                }
                val pdfBitmap=renderPdf(pdfFile)
                pdfBitmap?.let{
                    withContext(Dispatchers.Main){
                        imageView.setImageBitmap(it)
                        textView.text="PDF rendered successfully!"
                    }
                }
            }else{
                withContext(Dispatchers.Main){
                    textView.text="Falied to download PDF. Please try again."
                }
            }
        }
    }
    private suspend fun downloadPdf(url:String):File?{
        return withContext(Dispatchers.IO){
            val client=OkHttpClient()
            val request=Request.Builder().url(url).build()
            val response=client.newCall(request).execute()
            if(response.isSuccessful){
                val pdfFile=File(cacheDir,"downloaded_pdf.pdf")
                val fos=FileOutputStream(pdfFile)
                response.body?.byteStream()?.use { inputStream->
                    fos.use { outputStream->
                        inputStream.copyTo(outputStream)
                    }
                }
                pdfFile
            }else{
                null
            }
        }
    }
    private suspend fun renderPdf(file: File):Bitmap?{
        return withContext(Dispatchers.IO){
            val fileDescriptor=ParcelFileDescriptor.open(file,ParcelFileDescriptor.MODE_READ_ONLY)
            val pdfRenderer=PdfRenderer(fileDescriptor)
            val page=pdfRenderer.openPage(0)
            val width=page.width
            val height=page.height
            val bitmap=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888)
            page.render(bitmap,null,null,PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
            page.close()
            pdfRenderer.close()
            bitmap
        }
    }
}