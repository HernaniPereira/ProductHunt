package com.example.producthunt.ui.posts.detail.comments

import com.example.producthunt.data.repository.PostRepository
import com.example.producthunt.internal.lazyDeferred
import com.example.producthunt.ui.base.PostViewModel

class DetailCommentViewModel (
    private val productId: Long,
    private val postRepository: PostRepository
): PostViewModel(postRepository) {
    val post by lazyDeferred{
        postRepository.getCommentPostById(productId)
    }
}
