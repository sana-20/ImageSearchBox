package com.example.imagesearchbox.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.imagesearchbox.ui.box.MyBoxFragment
import com.example.imagesearchbox.ui.search.SearchFragment
import com.example.imagesearchbox.utils.Constants.TAB_MYPAGE
import com.example.imagesearchbox.utils.Constants.TAB_SEARCH

class FragmentTabAdapter(val fm: FragmentManager, private val tabs: List<String>) : FragmentPagerAdapter(fm){
    override fun getCount(): Int {
        return tabs.size
    }

    override fun getItem(position: Int): Fragment {
        return when(tabs[position]){
            TAB_SEARCH-> SearchFragment()
            TAB_MYPAGE-> MyBoxFragment()
            else -> SearchFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs[position]
    }
}