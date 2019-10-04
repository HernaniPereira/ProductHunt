package com.example.producthunt.data.db.dao


import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.producthunt.data.db.entity.Post

@Dao
interface PostDao {

   @Query("SELECT * FROM product_post ORDER BY productName")
   fun loadPosts() : DataSource.Factory<Int, Post>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun insert(message: List<Post>)

}