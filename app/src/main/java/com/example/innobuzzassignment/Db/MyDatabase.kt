package com.example.innobuzzassignment.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.innobuzzassignment.Dao.PostsDao
import com.example.innobuzzassignment.model.PostsItem

@Database(entities = [PostsItem::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun postsDao() : PostsDao

    companion object {
        private var dbInstance : MyDatabase? = null

        fun getInstance(context : Context) : MyDatabase? {
            if(dbInstance == null){
                synchronized(MyDatabase::class){
                    dbInstance = Room.databaseBuilder(context.applicationContext
                    ,MyDatabase::class.java,"app_database").build()
                }
            }
            return dbInstance
        }
    }
}