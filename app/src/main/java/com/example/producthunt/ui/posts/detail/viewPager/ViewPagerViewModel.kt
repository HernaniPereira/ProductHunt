package com.example.producthunt.ui.posts.detail.viewPager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.producthunt.data.repository.PostRepository
import com.example.producthunt.internal.lazyDeferred
import com.example.producthunt.ui.base.PostViewModel
import com.example.producthunt.ui.posts.detail.info.PostsDetailViewModel

class ViewPagerViewModel(
    private val productId: Long,
    private val postRepository: PostRepository
) : PostViewModel(postRepository) {
    val post by lazyDeferred{
        postRepository.getPostById(productId)
    }
}