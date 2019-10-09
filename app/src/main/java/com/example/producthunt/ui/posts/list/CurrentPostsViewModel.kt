package com.example.producthunt.ui.posts.list

import androidx.lifecycle.ViewModel
import com.example.producthunt.data.repository.PostRepository
import com.example.producthunt.internal.lazyDeferred
import com.example.producthunt.ui.base.PostViewModel
import org.threeten.bp.LocalDate

class CurrentPostsViewModel(
    private val postRepository : PostRepository
) : PostViewModel(postRepository) {

    val postEntries by lazyDeferred{
        postRepository.getPost(LocalDate.now())

    }
}
