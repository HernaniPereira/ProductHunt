package com.example.producthunt.ui.posts.detail.viewPager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.producthunt.data.repository.PostRepository
import com.example.producthunt.ui.posts.detail.info.PostsDetailViewModel

class ViewPagerViewModelFactory (
    private val productId: Long,
    private val postRepository: PostRepository
): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewPagerViewModel(
            productId,
            postRepository
        ) as T
    }

}