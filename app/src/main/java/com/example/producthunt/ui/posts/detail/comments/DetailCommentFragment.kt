package com.example.producthunt.ui.posts.detail.comments

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.producthunt.R
import com.example.producthunt.data.db.entity.CommentPostEntry
import com.example.producthunt.ui.base.ScopedFragment
import com.example.producthunt.ui.posts.detail.info.PostsDetailFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.current_posts_fragment.*
import kotlinx.android.synthetic.main.detail_comment_fragment.*
import kotlinx.android.synthetic.main.detail_comment_fragment.group_loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.factory
import org.kodein.di.generic.instance

class DetailCommentFragment : ScopedFragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()
    private var productId: Long = 0

    private val viewModelFactoryInstanceFactory
            : ((Long) -> DetailCommentViewModelFactory) by factory()

    private lateinit var viewModel: DetailCommentViewModel

    companion object {
        @JvmStatic
        fun votes(productid: Long) = DetailCommentFragment().apply {
            arguments = Bundle().apply {
                putLong("produto", productid)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.detail_comment_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactoryInstanceFactory(productId)
        )
            .get(DetailCommentViewModel::class.java)

        bindUI()
    }

    private fun bindUI() = launch(Dispatchers.Main) {
        val commentEntries = viewModel.post.await()

        commentEntries.observe(this@DetailCommentFragment, Observer { commentPostEntries ->
            if (commentPostEntries == null) return@Observer

            group_loading.visibility = View.GONE

            initRecyclerView(commentPostEntries.toCommentPostsItems())
        })
    }


    private fun List<CommentPostEntry>.toCommentPostsItems(): List<CommentPostItem> {
        return this.map {
            CommentPostItem(it)
        }
    }

    private fun initRecyclerView(items: List<CommentPostItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }
        commentsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@DetailCommentFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            Toast.makeText(this@DetailCommentFragment.context, "Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getLong("produto")?.let {
            productId = it
        }
    }


}
