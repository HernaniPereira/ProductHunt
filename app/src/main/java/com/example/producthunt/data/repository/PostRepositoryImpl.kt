package com.example.producthunt.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import com.example.producthunt.data.db.CurrentDay
import com.example.producthunt.data.db.dao.PostDao
import com.example.producthunt.data.db.entity.Post
import com.example.producthunt.data.network.PostNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime

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
        return withContext(Dispatchers.IO){
           // initPostData()
            postNetworkDataSource.fetchPost(CurrentDay.currentDay())
            return@withContext postNetworkDataSource.downloadedPost
        }


        /*
        return withContext(Dispatchers.IO){
            return@withContext postDao.loadPosts()
        }*/
    }
    private fun persistFetchedPost(fetchedPost: List<Post>){
        GlobalScope.launch ( Dispatchers.IO){
            val postList = fetchedPost
            postDao.insert(postList)
        }
    }

    override suspend fun getPostById(
        productId: Long
    ): LiveData<Post> {
        return withContext(Dispatchers.IO){
            return@withContext postDao.getDetailedPost(productId)
        }
    }

    /*private suspend fun fetchPost(){
        postNetworkDataSource.fetchPost(
            currentDay.currentDay()
        )
    }*/

    /*private suspend fun initPostData(){
        if(isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
           fetchPost()

    }*/

    private fun isFetchCurrentNeeded(lastFetchTime: org.threeten.bp.ZonedDateTime): Boolean{
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}