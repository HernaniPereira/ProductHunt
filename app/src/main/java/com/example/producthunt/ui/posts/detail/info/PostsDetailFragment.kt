package com.example.producthunt.ui.posts.detail.info

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide

import com.example.producthunt.R
import com.example.producthunt.internal.DateNotFoundException
import com.example.producthunt.ui.base.ScopedFragment
import com.example.producthunt.ui.posts.detail.viewPager.ViewPagerViewModel
import kotlinx.android.synthetic.main.posts_detail_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.factory

class PostsDetailFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModelFactoryInstanceFactory
            : ((Long) -> DetailPostViewModelFactory) by factory()

    lateinit var viewModel: PostsDetailViewModel

    private var productId : Long = 0

    companion object{

        @JvmStatic
        fun newInstance(productid:Long)= PostsDetailFragment().apply {
            arguments = Bundle().apply {
                putLong("produto",productid)
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.posts_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)





        viewModel = ViewModelProviders.of(this, viewModelFactoryInstanceFactory(productId))
            .get(PostsDetailViewModel::class.java)



       /* val model=ViewModelProviders.of(activity!!).get(Communicator::class.java)
        model.message.observe(this, object : Observer<Any> {
            override fun onChanged(o: Any?) {
                textView.text = o!!.toString()
            }
        })*/

      bindUI()
    }

    private fun bindUI() = launch (Dispatchers.Main){
        val postsEntries = viewModel.post.await()
        postsEntries.observe(this@PostsDetailFragment, Observer {entries ->
            if(entries == null) return@Observer

            updateTitle(entries.productName)
            // Log.d("info" , entries.toPostItems()[1].toString())

            Glide.with(this@PostsDetailFragment)
                .load(entries.postImage?.productLargeImgUrl)
                .into(itemScreenshot)


            buttonRedirect.setOnClickListener {
                val address = Uri.parse(entries?.redirectUrl)
                val openLinkIntent = Intent(Intent.ACTION_VIEW, address)
                startActivity(openLinkIntent)
            }
            itemName.text = entries?.productName ?: ""
            itemTagline.text = entries?.tagline ?: ""
            itemVotes.text = entries?.votesCount.toString()

        })
    }

    private fun updateTitle(productName: String?) {
        (activity as? AppCompatActivity)?.supportActionBar?.title=productName
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getLong("produto")?.let {
            productId= it
        }
    }

}
