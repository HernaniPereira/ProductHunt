package com.example.producthunt.data.db.dao


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.producthunt.data.db.entity.Post

@Dao
interface PostDao {

   @Query("SELECT * FROM product_post ORDER BY createdAt")
   fun loadPosts():  LiveData<List<Post>>
   //   fun loadPosts() : DataSource.Factory<Int, Post>
   @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun insert(message: List<Post>)

   @Query("select * from product_post where productId = :productId")
   fun getDetailedPost (productId: Long): LiveData<Post>

}