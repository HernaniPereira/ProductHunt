package com.example.producthunt.data.db.entity


import com.google.gson.annotations.SerializedName

data class InstallLink(
    @SerializedName("created_at")
    val createdAt: String,
    val id: Int,
    val platform: Any,
    @SerializedName("post_id")
    val postId: Int,
    @SerializedName("primary_link")
    val primaryLink: Boolean,
    val rating: Any,
    @SerializedName("redirect_url")
    val redirectUrl: String,
    @SerializedName("website_name")
    val websiteName: String
)