package com.example.imagesearchbox.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imagesearchbox.databinding.FragmentSearchBinding
import com.example.imagesearchbox.ui.search.adapter.SearchPagingAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(), SearchPagingAdapter.ClickInterface {

    private lateinit var searchAdapter: SearchPagingAdapter

    private lateinit var binding: FragmentSearchBinding

    private val searchViewModel: SearchViewModel by viewModel()

    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        searchAdapter = SearchPagingAdapter(this)

        binding.recyclerSearch.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = searchAdapter
        }

        job = lifecycleScope.launch {
            searchViewModel.getImages("kakao").collectLatest {
                searchAdapter.submitData(it)
            }
        }

    }

    override fun saveClicked(view: View) {
        view.isSelected = !view.isSelected
    }

}