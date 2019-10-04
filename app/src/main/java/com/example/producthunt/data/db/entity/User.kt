package com.example.producthunt.data.db.entity


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("created_at")
    val createdAt: String,
    val headline: String,
    val id: Int,
    @SerializedName("profile_url")
    val profileUrl: String,
    @SerializedName("twitter_username")
    val twitterUsername: String,
    val username: String,
    @SerializedName("website_url")
    val websiteUrl: String
)