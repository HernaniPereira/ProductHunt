package com.example.producthunt.ui.posts.detail.comments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.producthunt.R

class DetailCommentFragment : Fragment() {

    companion object {
        fun newInstance() = DetailCommentFragment()
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
