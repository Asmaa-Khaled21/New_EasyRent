package com.sakura.easyrent.control.interfaces

import com.sakura.easyrent.model.AccessToken
import com.sakura.easyrent.model.Contract
import com.sakura.easyrent.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

// Interface(API):
interface API {

    @POST("register/") // Method(Register):
    suspend fun register(@Body user: User): Response<User>

    @POST("api/token/") // Method(Login):
    suspend fun login(@Body user: User): Response<AccessToken>

    // Method(Contracts):
    @POST("contracts") suspend fun contract(@Body contract: Contract): Response<Contract>
    @GET("contracts") suspend fun contract(@Header("Authorization") token: String): Response<List<Contract>>

    // Method(UerMetaData):
    @GET("users/profiles/me/") suspend fun userMetaData(@Header("Authorization") token: String): Response<User>
}