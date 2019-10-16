package com.example.producthunt.ui.posts.detail.votes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.producthunt.R

class UpVotedFragment : Fragment() {

    companion object {
        fun newInstance() =
            UpVotedFragment()
    }

    private lateinit var viewModel: UpVotedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.up_voted_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UpVotedViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
