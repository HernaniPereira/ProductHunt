package com.example.producthunt.ui.posts.detail.comments

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.producthunt.R
import com.example.producthunt.ui.posts.detail.info.PostsDetailFragment

class DetailCommentFragment : Fragment() {


    companion object{

        @JvmStatic
        fun votes(productid:Long)= PostsDetailFragment().apply {
            arguments = Bundle().apply {
                putLong("produto1",productid)
            }
        }
    }
    private lateinit var viewModel: DetailCommentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_comment_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailCommentViewModel::class.java)
        // TODO: Use the ViewModel
    }



}
