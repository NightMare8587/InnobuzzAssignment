package com.example.innobuzzassignment.network

import com.example.innobuzzassignment.model.PostsItem
import retrofit2.Call
import retrofit2.http.GET

interface PostsApi {
    @GET("posts")
    fun getPosts() : Call<List<PostsItem>>
}