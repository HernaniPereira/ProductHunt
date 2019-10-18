package com.example.producthunt.data.db.entity


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.util.TableInfo
import com.google.gson.annotations.SerializedName


@Entity(tableName= "comment_posts", indices= [Index(value =["postId"], unique = false)])
data class CommentPostEntry(
    @SerializedName("post_id") @PrimaryKey(autoGenerate = true) val postId: Long,
    val body: String,
/*    @SerializedName("child_comments")
    val childComments: List<ChildComment>,*/
    @SerializedName("child_comments_count")
    val childCommentsCount: Int,
    @SerializedName("created_at")
    val createdAt: String,
    val hunter: Boolean,
    val id: Int,
    @SerializedName("live_guest")
    val liveGuest: Boolean,
    val maker: Boolean,
  /*  @SerializedName("parent_comment_id")
    val parentCommentId: Any,*/
    val sticky: Boolean,
    @SerializedName("subject_id")
    val subjectId: Int,
    @SerializedName("subject_type")
    val subjectType: String,
    val url: String,
    @Embedded val user: User?,

    val votes: Int
)