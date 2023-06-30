package com.example.innobuzzassignment.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("posts")
data class PostsItem(
    val body: String,
    @PrimaryKey
    val id: Int,
    val title: String,
    val userId: Int
)