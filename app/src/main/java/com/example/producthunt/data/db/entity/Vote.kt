package com.example.producthunt.data.db.entity


import com.google.gson.annotations.SerializedName

data class Vote(
    @SerializedName("created_at")
    val createdAt: String,
    val id: Int,
    @SerializedName("post_id")
    val postId: Int,
    val user: UserX,
    @SerializedName("user_id")
    val userId: Int
)