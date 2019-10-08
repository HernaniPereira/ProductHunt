package com.example.producthunt.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName ="product_post", indices = [Index(value = ["productId"], unique = true)] )
data class Post(
    @SerializedName("id") @PrimaryKey(autoGenerate = true) val productId:Long,
    @SerializedName("name") val productName:String?,
    val tagline:String?,
    @SerializedName("day") val day:String?,
    @SerializedName("comments_count") val commentsCount: Int?,
    @SerializedName("votes_count")val votesCount:Int?,
    @SerializedName("screenshot_url")
    @Embedded val postImage: PostImage?,
    @SerializedName("user")
    @Embedded val user: User?)

data class PostImage (
    @SerializedName("300px") var productSmallImgUrl: String?,
    @SerializedName("850px") var productLargeImgUrl: String?)