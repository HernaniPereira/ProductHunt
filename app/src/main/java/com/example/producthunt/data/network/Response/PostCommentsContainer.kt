package com.example.producthunt.data.network.Response


import com.example.producthunt.data.db.entity.*
import com.google.gson.annotations.SerializedName

data class PostCommentsContainer(
    val badges: List<Any>,
    @SerializedName("category_id")
    val categoryId: Any,
    val comments: List<CommentPostEntry>,
    @SerializedName("comments_count")
    val commentsCount: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("current_user")
    val currentUser: CurrentUser,
    val day: String,
    val description: String,
    @SerializedName("discussion_url")
    val discussionUrl: String,
    val exclusive: Any,
    @SerializedName("external_links")
    val externalLinks: List<Any>,
    val featured: Boolean,
    val id: Int,
    @SerializedName("install_links")
    val installLinks: List<InstallLink>,
    @SerializedName("ios_featured_at")
    val iosFeaturedAt: Boolean,
    @SerializedName("maker_inside")
    val makerInside: Boolean,
    val makers: List<Maker>,
    val media: List<Media>,
    val name: String,
    @SerializedName("negative_reviews_count")
    val negativeReviewsCount: Int,
    @SerializedName("neutral_reviews_count")
    val neutralReviewsCount: Int,
    val platforms: List<Any>,
    @SerializedName("positive_reviews_count")
    val positiveReviewsCount: Int,
    @SerializedName("product_state")
    val productState: String,
    @SerializedName("redirect_url")
    val redirectUrl: String,
    @SerializedName("related_links")
    val relatedLinks: List<Any>,
    @SerializedName("related_posts")
    val relatedPosts: List<RelatedPost>,
    @SerializedName("reviews_count")
    val reviewsCount: Int,
    val tagline: String,
    val thumbnail: Thumbnail,
    val topics: List<Topic>,
    val user: UserX,
    val votes: List<Vote>,
    @SerializedName("votes_count")
    val votesCount: Int
)