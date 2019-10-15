package com.example.producthunt.data.db.dao


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.producthunt.data.db.entity.Post

@Dao
interface PostDao {

   @Query("SELECT * FROM product_post order by createdAt")
   fun loadPosts():  LiveData<List<Post>>


   @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun insert(message: List<Post>)

   @Query("select * from product_post where productId = productId")
   fun getPostNonLive() : Post

   @Query("select * from product_post where day = :startDate order by productName")
   fun getSimplePosts(startDate: org.threeten.bp.LocalDate) : LiveData<List<Post>>

   @Query("select * from product_post where productId = :productId")
   fun getDetailedPost (productId: Long): LiveData<Post>

}