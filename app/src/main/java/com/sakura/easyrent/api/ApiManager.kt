package com.sakura.easyrent.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class  ApiManager {
    companion object{
        var retrofit:Retrofit?=null

     private  fun geRetrofitInstance(): Retrofit {
         if (retrofit==null) {
             val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger{
                 override fun log(message: String) {
                     Log.e("api",message)
                 }
             })
             interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
             val client= OkHttpClient.Builder().addInterceptor(interceptor).build()

             retrofit = Retrofit.Builder()
                 .client(client)
                 .baseUrl("https://guarded-scrubland-74784.herokuapp.com/")
                 .addConverterFactory(GsonConverterFactory.create())
                 .build()
         }
           return retrofit!!
       }

        fun getApis(): Webservices {
            return geRetrofitInstance()
                .create(Webservices::class.java);
        }
    }
}