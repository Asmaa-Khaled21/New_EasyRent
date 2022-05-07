package com.sakura.easyrent.api

import com.sakura.easyrent.database.model.Post
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SimpleApi {

    @GET("register/")
    fun getRegisterLogin()

    @POST("/register/")
    suspend fun pushPost( @Body post:Post):Response<Post>
    
}