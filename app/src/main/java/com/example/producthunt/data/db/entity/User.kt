package com.example.producthunt.data.db.entity


import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val userId: Int?,
    val username: String?,
    val headline: String?,
    val name: String?,
    @Embedded @SerializedName("image_url") val imageUrl: UserImage?)



class UserImage (
    @SerializedName("48px") val userSmallImgUrl: String?,
    @SerializedName("73px") val userLargeImgUrl: String?)