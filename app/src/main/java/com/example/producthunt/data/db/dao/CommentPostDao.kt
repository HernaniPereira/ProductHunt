package com.example.producthunt.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.producthunt.data.db.entity.CommentPostEntry

@Dao
interface CommentPostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(commentPostEntries: List<CommentPostEntry>)


    @Query("select * from comment_posts  where postId = :productId ")
    fun getDetailedComment (productId: Long): LiveData<List<CommentPostEntry>>
}