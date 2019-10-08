package com.example.producthunt.data.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.producthunt.data.db.CurrentDay
import com.example.producthunt.data.db.dao.PostDao
import com.example.producthunt.data.db.entity.Post
import com.example.producthunt.data.network.PostNetworkDataSource
import com.example.producthunt.data.network.Response.CurrentPostsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate

class PostRepositoryImpl(
     private val postDao: PostDao,
     private val postNetworkDataSource: PostNetworkDataSource
) : PostRepository {

    init {
        postNetworkDataSource.apply {
            downloadedPost.observeForever{ newPost ->
                persistFetchedPost(newPost)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getPost(
        date: LocalDate
    ): LiveData<List<Post>> {
        postNetworkDataSource.fetchPost(CurrentDay.currentDay())
        return postNetworkDataSource.downloadedPost

        /*
        return withContext(Dispatchers.IO){
            return@withContext postDao.loadPosts()
        }*/
    }
    private fun persistFetchedPost(postList: List<Post>){
        GlobalScope.launch ( Dispatchers.IO){
            postDao.insert(postList)
            Log.e("dadda", "adawda")
        }
    }

    override suspend fun getPostById(
        productId: Long
    ): LiveData<Post> {
        return withContext(Dispatchers.IO){
            return@withContext postDao.getDetailedPost(productId)
        }
    }
}