package com.example.producthunt.data.db.entity


import com.google.gson.annotations.SerializedName

data class RelatedPost(
    @SerializedName("category_id")
    val categoryId: Any,
    @SerializedName("comments_count")
    val commentsCount: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("current_user")
    val currentUser: CurrentUser,
    val day: String,
    @SerializedName("discussion_url")
    val discussionUrl: String,
    val exclusive: Any,
    val featured: Boolean,
    val id: Int,
    @SerializedName("ios_featured_at")
    val iosFeaturedAt: Boolean,
    @SerializedName("maker_inside")
    val makerInside: Boolean,
    val makers: List<Maker>,
    val name: String,
    val platforms: List<Any>,
    @SerializedName("product_state")
    val productState: String,
    @SerializedName("redirect_url")
    val redirectUrl: String,
    val tagline: String,
    val thumbnail: Thumbnail,
    val topics: List<Topic>,
    val user: UserX,
    @SerializedName("votes_count")
    val votesCount: Int
)