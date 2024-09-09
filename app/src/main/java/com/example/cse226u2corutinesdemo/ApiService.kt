package com.example.cse226u2corutinesdemo

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET
    suspend fun downloadImage(@retrofit2.http.Url url:String):
            Response<ResponseBody>
}