package com.example.producthunt.data.db.entity


import com.google.gson.annotations.SerializedName

data class CurrentUser(
    @SerializedName("commented_on_post")
    val commentedOnPost: Boolean,
    @SerializedName("voted_for_post")
    val votedForPost: Boolean
)