package com.example.innobuzzassignment.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.innobuzzassignment.model.PostsItem

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(list : List<PostsItem>)

    @Query("SELECT * FROM posts")
    suspend fun getPostsFromDb() : List<PostsItem>
}