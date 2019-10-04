package com.example.producthunt.data.network.Response


import com.example.producthunt.data.db.entity.Post

data class CurrentPostsResponse(
    val posts: List<Post>
)