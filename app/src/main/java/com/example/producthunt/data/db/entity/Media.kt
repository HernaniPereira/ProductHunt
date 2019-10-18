package com.example.producthunt.data.db.entity


import com.google.gson.annotations.SerializedName

data class Media(
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("kindle_asin")
    val kindleAsin: Any,
    @SerializedName("media_type")
    val mediaType: String,
    val metadata: Metadata,
    @SerializedName("original_height")
    val originalHeight: Int,
    @SerializedName("original_width")
    val originalWidth: Int,
    val platform: Any,
    val priority: Int,
    @SerializedName("video_id")
    val videoId: Any
)