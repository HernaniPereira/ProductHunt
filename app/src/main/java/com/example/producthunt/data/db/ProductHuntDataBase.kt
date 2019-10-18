package com.example.producthunt.data.db

import android.content.Context
import androidx.room.*
import com.example.producthunt.data.db.dao.CommentPostDao
import com.example.producthunt.data.db.dao.PostDao
import com.example.producthunt.data.db.entity.CommentPostEntry
import com.example.producthunt.data.db.entity.Post

@Database(
    entities = [Post::class, CommentPostEntry::class], version = 1,exportSchema = false)
@TypeConverters(LocalDateConverter::class)
abstract class ProductHuntDataBase : RoomDatabase(){

    abstract fun postDao() : PostDao
    abstract fun commentPostDao() : CommentPostDao

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