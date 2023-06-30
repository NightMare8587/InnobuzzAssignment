package com.example.innobuzzassignment.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {
    private var postsApi : PostsApi? = null
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun getInstance() : PostsApi? {
        if(postsApi == null){
            postsApi = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PostsApi::class.java)
        }

        return postsApi
    }
}