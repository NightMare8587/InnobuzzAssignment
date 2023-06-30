package com.example.innobuzzassignment.fragments

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.innobuzzassignment.Adapter.PostsAdapter
import com.example.innobuzzassignment.Dao.PostsDao
import com.example.innobuzzassignment.Db.MyDatabase
import com.example.innobuzzassignment.R
import com.example.innobuzzassignment.ViewModel.PostModelFactory
import com.example.innobuzzassignment.ViewModel.PostsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DisplayFrag : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: Editor
    private lateinit var recyclerView: RecyclerView
    private lateinit var postsAdapter: PostsAdapter
    private lateinit var postsModel: PostsModel
    private lateinit var myDatabase: MyDatabase
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.displayfrag_layout,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialise(view)

        // if data is already stored then get it from room
        if(sharedPreferences.contains("dataStored")){
            postsModel.postsData.observe(viewLifecycleOwner) {
                postsAdapter.setMovies(it)
            }
            postsModel.getAllPostsFromDB()
        }else{
            // first time getting data from API
            postsModel.postsData.observe(viewLifecycleOwner) {
                postsAdapter.setMovies(it)
            }
            editor.putString("dataStored","yes")
            editor.apply()
            postsModel.getAllPosts()
        }
    }

    private fun initialise(view: View) {
        myDatabase = MyDatabase.getInstance(view.context)!!
        sharedPreferences = view.context.getSharedPreferences("info",MODE_PRIVATE)
        editor = sharedPreferences.edit()
        recyclerView = view.findViewById(R.id.recyclerView)
        postsAdapter = PostsAdapter(view.context)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = postsAdapter
        postsModel = ViewModelProvider(this,PostModelFactory(myDatabase))[PostsModel::class.java]
    }
}