package com.example.producthunt.data.repository

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import com.example.producthunt.data.db.dao.CommentPostDao
import com.example.producthunt.data.db.dao.PostDao
import com.example.producthunt.data.db.entity.CommentPostEntry
import com.example.producthunt.data.db.entity.Post
import com.example.producthunt.data.network.PostNetworkDataSource
import com.example.producthunt.data.network.Response.CommentPostsResponse
import com.example.producthunt.data.network.Response.CurrentPostsResponse
import com.example.producthunt.data.preferences.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime

class PostRepositoryImpl(
    private val postDao: PostDao,
    private val postNetworkDataSource: PostNetworkDataSource,
    private val commentPostDao: CommentPostDao,
    private val prefs: SharedPreferences
) : PostRepository {

    init {
        postNetworkDataSource.apply {
            downloadedPost.observeForever { newPost ->
                persistFetchedPost(newPost)
                prefs.savelastSavedAt(ZonedDateTime.now().toString())
            }
            downloadedCommentPost.observeForever { newComments ->
                persistFetchedCommentPost(newComments)
                prefs.savelastSavedAt(ZonedDateTime.now().toString())
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getPostList(
        startDate: LocalDate
    ): LiveData<List<Post>> {
        return withContext(Dispatchers.IO) {
            initPostData(startDate)
            return@withContext postDao.getSimplePosts(startDate)
        }
    }

    override suspend fun getCommentPostById(
        productId: Long
    ): LiveData<List<CommentPostEntry>> {
        return withContext(Dispatchers.IO) {
            initCommentData(productId)
            return@withContext commentPostDao.getDetailedComment(productId)
        }
    }

    private fun persistFetchedPost(fetchedPost: CurrentPostsResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            val postList = fetchedPost.posts
            postDao.insert(postList)
        }
    }

    private fun persistFetchedCommentPost(fetchedCommentPost: CommentPostsResponse){
        GlobalScope.launch(Dispatchers.IO){
            val commentPostList = fetchedCommentPost.post.comments
            commentPostDao.insert(commentPostList)//retorna bem
        }
    }

    override suspend fun getPostById(
        productId: Long
    ): LiveData<Post> {
        return withContext(Dispatchers.IO) {
            return@withContext postDao.getDetailedPost(productId)
        }
    }

    private suspend fun fetchPost(startDate: LocalDate) {
        postNetworkDataSource.fetchPost(
            startDate.toString()
        )
    }

    private suspend fun initCommentData(productId: Long){
        val lastCommentSaved = prefs.getlastSavedAt()
        fetchCommentPosts(productId)
        //commentPostDao.getDetailedComment(productId)

        /*if(lastCommentSaved == null || isFetchCurrentNeeded(ZonedDateTime.parse(lastCommentSaved))){
            fetchCommentPosts(productId)
        }else{
            postDao.getDetailedPost(productId)
        }*/
    }

    private suspend fun initPostData(startDate: LocalDate) {

        val lastSavedAt = prefs.getlastSavedAt()
       if (lastSavedAt == null || isFetchCurrentNeeded(ZonedDateTime.parse(lastSavedAt))) {
            fetchPost(startDate)
        } else {
            postDao.getSimplePosts(startDate)
        }
    }

    private suspend fun fetchCommentPosts(productId: Long){
        postNetworkDataSource.fetchCommentPosts(
            productId
        )
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}