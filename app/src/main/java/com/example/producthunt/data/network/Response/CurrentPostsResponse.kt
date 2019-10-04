package com.example.producthunt.data.network.Response


import com.example.producthunt.data.db.entity.Post
import com.google.gson.annotations.SerializedName

data class CurrentPostsResponse(
    @SerializedName("posts")
    val posts: List<Post>
)