package com.example.producthunt.ui.posts.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.producthunt.R
import com.example.producthunt.ui.posts.detail.comments.DetailCommentFragment
import com.example.producthunt.ui.posts.detail.info.PostsDetailFragment

import com.google.android.material.tabs.TabLayout
import org.w3c.dom.Comment


class ViewPagerFragment : Fragment() {

    internal var view: View? = null
    internal var adapter: Adapter? = null
    private lateinit var viewModel: ViewPagerViewModel

    private fun setupViewPager(viewPager: ViewPager) {
        adapter = Adapter(childFragmentManager)
        adapter?.addFragment(PostsDetailFragment(), "1")
        adapter?.addFragment(DetailCommentFragment(), "Comments")
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
        viewModel = ViewModelProviders.of(this).get(ViewPagerViewModel::class.java)
        // TODO: Use the ViewModel

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


}

