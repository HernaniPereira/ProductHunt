package com.example.producthunt.data.db.entity


import com.google.gson.annotations.SerializedName

data class Thumbnail(
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("media_type")
    val mediaType: String,
    val metadata: Metadata
)