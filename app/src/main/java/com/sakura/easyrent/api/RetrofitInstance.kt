package com.sakura.easyrent.api

import com.sakura.easyrent.api.Constans.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private  val retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api :SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }
}