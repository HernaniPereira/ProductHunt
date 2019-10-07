package com.example.producthunt.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.producthunt.data.db.entity.Post
import org.threeten.bp.LocalDate

interface PostRepository {

    suspend fun getPost(date: LocalDate) : LiveData<List<Post>>

}