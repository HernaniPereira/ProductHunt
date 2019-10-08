package com.example.producthunt.ui.posts.list

import com.example.producthunt.R
import com.example.producthunt.data.db.entity.Post
import com.example.producthunt.internal.glide.GlideApp
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_post.*

class PostItem (
    val postEntry : Post
) : Item(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
       viewHolder.apply {
           id.text= postEntry.productId.toString()
           card_product_title.text = postEntry.productName
           card_product_description.text = postEntry.tagline
           card_product_upvotes.text = postEntry?.votesCount.toString()
           card_product_comments_total.text = postEntry?.commentsCount.toString()
           card_product_view_comments.text = postEntry?.user?.username
           updatePostImage()
           updateUserImage()
       }
    }

    override fun getLayout() = R.layout.item_post

    private fun ViewHolder.updatePostImage(){
        GlideApp.with(this.containerView)
            .load(postEntry?.postImage?.productLargeImgUrl)
            .into(card_product_image)
    }

    private fun ViewHolder.updateUserImage(){
        GlideApp.with(this.containerView)
            .load(postEntry?.user?.imageUrl?.userSmallImgUrl)
            .into(profile_image)

    }


}