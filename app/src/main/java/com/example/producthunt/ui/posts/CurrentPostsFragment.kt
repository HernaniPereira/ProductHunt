package com.example.producthunt.ui.posts

import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer

import com.example.producthunt.R
import com.example.producthunt.data.network.ApiProductHuntService
import com.example.producthunt.data.db.CurrentDay
import com.example.producthunt.data.network.ConnectivityInterceptorImpl
import com.example.producthunt.data.network.PostNetworkDataSourceImpl
import kotlinx.android.synthetic.main.current_posts_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CurrentPostsFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentPostsFragment()
    }

    private lateinit var viewModel: CurrentPostsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_posts_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentPostsViewModel::class.java)

        val apiService = ApiProductHuntService(
            ConnectivityInterceptorImpl(this.context!!)
        )
        val postNetworkDataSource = PostNetworkDataSourceImpl (apiService)

        postNetworkDataSource.downloadedPost.observe(this, Observer{
            textview.text = it.toString()

        })

        GlobalScope.launch(Dispatchers.Main){
            postNetworkDataSource.fetchPost(CurrentDay.currentDay())
            Log.e("Dia de hoje: " , CurrentDay.currentDay())
        }

    }

}
