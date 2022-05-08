package com.sakura.easyrent.control.managers

import com.sakura.easyrent.control.interfaces.API
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/** [APIManager] kotlin object for creating any retrofit instance as a singleton pattern */
object APIManager {

    // Fields:
    const val BASE_URL: String = "https://guarded-scrubland-74784.herokuapp.com/"

    // Method(Instance):
    fun instance(): API {
        val client = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        // Setting:
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        // Adding:
        client.addInterceptor(interceptor)
        // Returning:
        return Retrofit.Builder().apply {
            // Applying:
            this.baseUrl(BASE_URL)
            this.client(client.build())
            this.addConverterFactory(GsonConverterFactory.create())
            // Creating:
        }.build().create(API::class.java)
    }
}