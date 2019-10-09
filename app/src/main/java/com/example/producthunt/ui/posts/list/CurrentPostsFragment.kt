package com.example.producthunt.ui.posts.list

import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.producthunt.data.db.entity.Post
import com.example.producthunt.ui.base.ScopedFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.current_posts_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.producthunt.R
import com.example.producthunt.data.db.CurrentDay
import com.example.producthunt.ui.posts.detail.Communicator
import com.example.producthunt.ui.posts.list.CurrentPostsFragmentDirections.actionDetail


class CurrentPostsFragment : ScopedFragment(), KodeinAware {


    override val kodein by closestKodein()
    private val viewModelFactory: PostListViewModelFactory by instance()


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

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CurrentPostsViewModel::class.java)

        (activity as AppCompatActivity).supportActionBar!!.title = "News"
        bindUI()
    }

    private fun bindUI() = launch(Dispatchers.Main){
        val postsEntries = viewModel.postEntries.await()

        postsEntries.observe(this@CurrentPostsFragment, Observer {entries ->
                if (entries == null)return@Observer

            group_loading.visibility = View.GONE

            initRecylcerView(entries!!.toPostItems())

        })
    }

    private fun List<Post>.toPostItems() : List<PostItem>{
        return this.map {
            PostItem(it)
        }
    }

    private fun initRecylcerView(items: List<PostItem>){
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@CurrentPostsFragment.context)
            adapter= groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as? PostItem)?.let {
                showPostDetail(it.postEntry.productId, view)
            }
        }
    }
    private fun showPostDetail(productId: Long, view: View){
        val actionDetail = CurrentPostsFragmentDirections.actionDetail(productId)
        Navigation.findNavController(view).navigate(actionDetail)
    }



}
