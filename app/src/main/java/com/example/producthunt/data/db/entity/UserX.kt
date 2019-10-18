package com.example.producthunt.data.db.entity


import com.google.gson.annotations.SerializedName

data class UserX(
    @SerializedName("created_at")
    val createdAt: String,
    val headline: Any,
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: ImageUrl,
    val name: String,
    @SerializedName("profile_url")
    val profileUrl: String,
    @SerializedName("twitter_username")
    val twitterUsername: String,
    val username: String,
    @SerializedName("website_url")
    val websiteUrl: String
)
class ImageUrl (
    @SerializedName("48px") val userSmallImgUrl: String?,
    @SerializedName("73px") val userLargeImgUrl: String?)