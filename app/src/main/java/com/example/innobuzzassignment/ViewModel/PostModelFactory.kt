package com.example.innobuzzassignment.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.innobuzzassignment.Dao.PostsDao
import com.example.innobuzzassignment.Db.MyDatabase
import com.example.innobuzzassignment.network.PostsApi
import com.example.innobuzzassignment.network.RetrofitObject

class PostModelFactory constructor(private val postsDao: MyDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostsModel::class.java)) {
            return PostsModel(postsDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}