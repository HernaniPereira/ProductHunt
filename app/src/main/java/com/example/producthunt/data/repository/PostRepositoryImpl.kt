package com.example.producthunt.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
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
    override suspend fun getPostList(
        startDate: LocalDate
    ): LiveData<List<Post>> {
        return withContext(Dispatchers.IO){
           initPostData()
            //postNetworkDataSource.fetchPost(CurrentDay.currentDay())
            return@withContext postDao.getSimplePosts(startDate)
        }

        /*
        return withContext(Dispatchers.IO){
            return@withContext postDao.loadPosts()
        }*/
    }
    private fun persistFetchedPost(fetchedPost: CurrentPostsResponse){
        GlobalScope.launch ( Dispatchers.IO){
            val postList = fetchedPost.posts
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

    private suspend fun fetchPost(){
        postNetworkDataSource.fetchPost(
            CurrentDay.currentDay()
        )
    }

    private suspend fun initPostData(){

        val lastP = postDao.getPostNonLive()
        //if(isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
        fetchPost()

        /*if (lastP == null) {
            fetchPost()

            return
        }
        if(isFetchCurrentNeeded(lastP.)){
            fetchPost()
        }*/

    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean{
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }


//    29 5
  //  45 3
    //17 5
}