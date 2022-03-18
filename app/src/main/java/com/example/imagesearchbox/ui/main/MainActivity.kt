package com.example.imagesearchbox.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.imagesearchbox.R
import com.example.imagesearchbox.databinding.ActivityMainBinding
import com.example.imagesearchbox.ui.box.BoxFragment
import com.example.imagesearchbox.ui.search.SearchFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private val searchFragment by lazy { SearchFragment() }

    private val boxFragment by lazy { BoxFragment() }


    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initTabLayout()
    }


    private fun initTabLayout() {
        supportFragmentManager.beginTransaction().add(R.id.frameLayout, searchFragment, "1").commit()
        binding.tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0->{
                        replaceView(searchFragment)
                    }
                    1->{
                        replaceView(boxFragment)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun replaceView(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()

    }

}