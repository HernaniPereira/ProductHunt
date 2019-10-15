package com.example.producthunt.ui.posts.detail.info

import com.example.producthunt.data.repository.PostRepository
import com.example.producthunt.internal.lazyDeferred
import com.example.producthunt.ui.base.PostViewModel

class PostsDetailViewModel(
    private val productId: Long,
    private val postRepository: PostRepository
) : PostViewModel(postRepository) {
    val post by lazyDeferred{
        postRepository.getPostById(productId)
    }
}
