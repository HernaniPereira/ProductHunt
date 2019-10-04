package com.example.producthunt.data.db.entity


import com.google.gson.annotations.SerializedName

data class Topic(
    val id: Int,
    val name: String,
    val slug: String
)