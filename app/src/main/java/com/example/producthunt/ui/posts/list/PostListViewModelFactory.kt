package com.example.producthunt.ui.posts.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.producthunt.data.repository.PostRepository

class PostListViewModelFactory (
    private val postRepository : PostRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrentPostsViewModel(
            postRepository
        ) as T
    }
}