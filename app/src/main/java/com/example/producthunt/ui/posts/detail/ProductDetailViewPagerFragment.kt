package com.example.producthunt.ui.posts.detail


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.producthunt.R
import com.example.producthunt.internal.DateNotFoundException
import com.example.producthunt.ui.base.ScopedFragment
import com.example.producthunt.ui.posts.detail.comments.DetailCommentFragment
import com.example.producthunt.ui.posts.detail.info.PostsDetailFragment
import com.example.producthunt.ui.posts.detail.info.PostsDetailFragmentArgs
import com.example.producthunt.ui.posts.detail.viewPager.ViewPagerViewModel
import com.example.producthunt.ui.posts.detail.viewPager.ViewPagerViewModelFactory
import com.example.producthunt.ui.posts.detail.votes.UpVotedFragment

import com.google.android.material.tabs.TabLayout
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.factory


class ProductDetailViewPagerFragment : ScopedFragment(), KodeinAware {

    internal var view: View? = null
    internal var adapter: Adapter? = null

    override val kodein by closestKodein()


  /*  private val viewModelFactoryInstanceFactory
            : ((Long) -> ViewPagerViewModelFactory) by factory()*/


    //private lateinit var viewModel: ViewPagerViewModel


    private fun setupViewPager(viewPager: ViewPager) {
        val safeArgs =arguments?.let {
            PostsDetailFragmentArgs.fromBundle(
                it
            )
        }
        adapter =
            Adapter(
                childFragmentManager
            )
        val productId = safeArgs?.productId ?: throw DateNotFoundException()
        adapter?.addFragment(PostsDetailFragment.newInstance(productId), "Info")
        adapter?.addFragment(UpVotedFragment(),"Upvoted")
        adapter?.addFragment(DetailCommentFragment.votes(productId), "Comments")
        viewPager.adapter = adapter

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.view_pager_fragment, container, false)
        super.onCreate(savedInstanceState)
        // ButterKnife.bind(this,view)
        val viewPager = view?.findViewById<ViewPager>(R.id.viewPager)
        viewPager?.let { setupViewPager(it) }
        val tabLayout = view?.findViewById<TabLayout>(R.id.tabs)
        tabLayout?.setupWithViewPager(viewPager)
        return view
    }
/*
    override fun onDestroyView() {
        super.onDestroyView()
        ButterKnife.unbind(this)
    }*/

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val safeArgs =arguments?.let {
            PostsDetailFragmentArgs.fromBundle(
                it
            )
        }

        val productId = safeArgs?.productId ?: throw DateNotFoundException()


        Log.e("productId", productId.toString() )
       // view?.let { showPostDetail(productId, it) }

    }

    internal class Adapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
        private val mFragments: MutableList<Fragment> = ArrayList()
        private val mFragmentTitles: MutableList<String> = ArrayList()
        fun addFragment(fragment: Fragment, title: String) {
            mFragments.add(fragment)
            mFragmentTitles.add(title)
        }

        override fun getItem(position: Int): Fragment {
            return mFragments[position]
        }

        override fun getCount(): Int {
            return mFragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitles[position]
        }
    }
/*
    private fun showPostDetail(productId: Long, view: View){
        val actionDetail = ViewPagerFragmentDirections.actionDetail(productId)
        Navigation.findNavController(view).navigate(actionDetail)
    }*/


}

