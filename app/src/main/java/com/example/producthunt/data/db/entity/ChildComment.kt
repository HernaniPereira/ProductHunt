package com.example.producthunt.data.db.entity


import com.google.gson.annotations.SerializedName

data class ChildComment(
    val body: String,
    @SerializedName("child_comments")
    val childComments: List<Any>,
    @SerializedName("child_comments_count")
    val childCommentsCount: Int,
    @SerializedName("created_at")
    val createdAt: String,
    val hunter: Boolean,
    val id: Int,
    @SerializedName("live_guest")
    val liveGuest: Boolean,
    val maker: Boolean,
    @SerializedName("parent_comment_id")
    val parentCommentId: Int,
    val post: Post,
    @SerializedName("post_id")
    val postId: Int,
    val sticky: Boolean,
    @SerializedName("subject_id")
    val subjectId: Int,
    @SerializedName("subject_type")
    val subjectType: String,
    val url: String,
    val user: UserX,
    @SerializedName("user_id")
    val userId: Int,
    val votes: Int
)