package com.example.producthunt.data.network

import android.util.Log
import com.example.producthunt.data.network.Response.CommentPostsResponse
import com.example.producthunt.data.network.Response.CurrentPostsResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


const val API_KEY = "Bearer ggQelQNanZQ0LXCrR4Z_RFq03uy6ocBxG6P6uiRRWX0"

interface ApiProductHuntService {
    @GET("posts/all")
    fun getCurrentPosts(
        @Query("day") day:String/*,
        @Query("page") page:Int,
        @Query("per_page") pageSize:Int*/) : Deferred<CurrentPostsResponse>

    @GET("posts/{postId}")
    fun getCommentPosts(
        @Path("postId") postId:Long
    ):Deferred<CommentPostsResponse>

    companion object{
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): ApiProductHuntService {
            val requestInterceptor = Interceptor { chain ->

                val original = chain.request()

                val requestBuilder = original.newBuilder()
                    .header("Authorization", API_KEY)

                val request = requestBuilder.build()
                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.producthunt.com/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiProductHuntService::class.java)

        }
    }
}