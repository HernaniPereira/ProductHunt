package com.example.producthunt.ui.posts.detail.comments

import com.bumptech.glide.Glide
import com.example.producthunt.R
import com.example.producthunt.data.db.entity.CommentPostEntry
import com.example.producthunt.internal.glide.GlideApp
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.comment_item.*

class CommentPostItem(
    val commentPostEntry: CommentPostEntry
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            postCommentTextView.text = commentPostEntry.body
            updateUserImage()
        }
    }

    override fun getLayout() = R.layout.comment_item

    private fun ViewHolder.updateUserImage(){
        GlideApp.with(this.containerView)
            .load(commentPostEntry.user?.imageUrl?.userSmallImgUrl)
            .into(commentUserImg)
    }
}