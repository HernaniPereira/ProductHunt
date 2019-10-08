package com.example.producthunt.ui.base

import androidx.lifecycle.ViewModel
import com.example.producthunt.data.repository.PostRepository

open class PostViewModel(
    private val postRepository: PostRepository
) : ViewModel(){
    //private val page
}