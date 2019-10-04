package com.example.producthunt.data.repository

interface PostRepository {

    suspend fun getPost(metric: Boolean)

}