package com.example.producthunt.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.producthunt.data.db.dao.PostDao
import com.example.producthunt.data.db.entity.Post

@Database(
    entities = [Post::class], version =1)
abstract class ProductHuntDataBase : RoomDatabase(){

    abstract fun postDao() : PostDao

    companion object{
        @Volatile private var instance: ProductHuntDataBase? = null


        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: builDatabase(context).also{instance = it}
        }

        private fun builDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ProductHuntDataBase::class.java,"ph.db")
                .build()
        //fun getInstance( context: Context) : ProductHuntDataBase =

    }
}