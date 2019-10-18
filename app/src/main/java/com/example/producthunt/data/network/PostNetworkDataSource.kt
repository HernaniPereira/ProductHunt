package com.example.producthunt.data.network

import androidx.lifecycle.LiveData
import com.example.producthunt.data.db.entity.Post
import com.example.producthunt.data.network.Response.CommentPostsResponse
import com.example.producthunt.data.network.Response.CurrentPostsResponse

interface PostNetworkDataSource {

    val downloadedPost: LiveData<CurrentPostsResponse>
    val downloadedCommentPost: LiveData<CommentPostsResponse>

    suspend fun fetchPost(
        day : String
       /* page : Int,
        pageSize : Int*/
    )

    suspend fun fetchCommentPosts(
        postId : Long
    )
}