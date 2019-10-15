package com.example.producthunt.ui.posts.detail.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.producthunt.data.repository.PostRepository

class DetailPostViewModelFactory (
    private val productId: Long,
    private val postRepository: PostRepository
):ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostsDetailViewModel(
            productId,
            postRepository
        ) as T
    }

}