package com.example.imagesearchbox.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.imagesearchbox.databinding.ActivityMainBinding
import com.example.imagesearchbox.utils.Constants.TAB_MYPAGE
import com.example.imagesearchbox.utils.Constants.TAB_SEARCH
import com.example.imagesearchbox.view.adapter.FragmentTabAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initTabLayout()
    }

    private fun initTabLayout() {
        binding.viewPager.adapter = FragmentTabAdapter(supportFragmentManager, listOf(TAB_SEARCH, TAB_MYPAGE))
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

}