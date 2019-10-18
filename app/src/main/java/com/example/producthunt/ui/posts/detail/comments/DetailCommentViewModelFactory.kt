package com.example.producthunt.ui.posts.detail.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.producthunt.data.repository.PostRepository
import com.example.producthunt.ui.posts.detail.info.PostsDetailViewModel

class DetailCommentViewModelFactory(
    private val productId: Long,
    private val postRepository: PostRepository
):ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailCommentViewModel(
            productId,
            postRepository
        ) as T
    }

}