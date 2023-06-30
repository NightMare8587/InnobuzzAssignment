package com.example.innobuzzassignment.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.innobuzzassignment.Dao.PostsDao
import com.example.innobuzzassignment.Db.MyDatabase
import com.example.innobuzzassignment.model.PostsItem
import com.example.innobuzzassignment.network.RetrofitObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsModel(postsDao : MyDatabase) : ViewModel() {
    val postsData = MutableLiveData<List<PostsItem>>()
    val dao = postsDao.postsDao()


    fun getAllPostsFromDB() {
        viewModelScope.launch {
            val dataList = dao.getPostsFromDb()
            postsData.postValue(dataList)
        }
    }

    fun getAllPosts() {
        RetrofitObject.getInstance()?.getPosts()?.enqueue(object : Callback<List<PostsItem>> {
            override fun onResponse(
                call: Call<List<PostsItem>>,
                response: Response<List<PostsItem>>
            ) {
                if(response.code() == 200){
                    postsData.postValue(response.body())
                   viewModelScope.launch {
                       response.body()?.let { dao.insertData(response.body()!!)  }
                   }
                }
            }

            override fun onFailure(call: Call<List<PostsItem>>, t: Throwable) {

            }

        })
    }
}