package com.sakura.easyrent.api

import com.sakura.easyrent.database.model.Post
import retrofit2.Response

class Repository {

    suspend fun pushPost(post:Post):Response<Post>
    {
        return RetrofitInstance.api.pushPost(post)
    }


}