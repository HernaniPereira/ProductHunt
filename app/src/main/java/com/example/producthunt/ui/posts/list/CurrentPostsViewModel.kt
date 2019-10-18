package com.example.producthunt.ui.posts.list

import com.example.producthunt.data.repository.PostRepository
import com.example.producthunt.internal.lazyDeferred
import com.example.producthunt.ui.base.PostViewModel
import org.threeten.bp.LocalDate

class CurrentPostsViewModel(
    private val postRepository : PostRepository
) : PostViewModel(postRepository) {

    val postEntries by lazyDeferred{
        postRepository.getPostList(LocalDate.now())

    }
}
