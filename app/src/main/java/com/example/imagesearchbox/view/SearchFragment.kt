package com.example.imagesearchbox.view

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imagesearchbox.R
import com.example.imagesearchbox.databinding.FragmentSearchBinding
import com.example.imagesearchbox.model.MyBox
import com.example.imagesearchbox.model.Response
import com.example.imagesearchbox.utils.hideSoftKeyboard
import com.example.imagesearchbox.view.adapter.SearchPagingAdapter
import com.example.imagesearchbox.viewmodel.MyBoxViewModel
import com.example.imagesearchbox.viewmodel.SearchViewModel
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding>(), SearchPagingAdapter.ClickInterface {

    private val searchViewModel: SearchViewModel by viewModel()

    private val myBoxViewModel: MyBoxViewModel by viewModel()

    private lateinit var searchAdapter: SearchPagingAdapter

    private var job: Job? = null

    override fun getViewBinding(): FragmentSearchBinding =  FragmentSearchBinding.inflate(layoutInflater)

    override fun initView() {
        searchAdapter = SearchPagingAdapter(this)

        binding.recyclerSearch.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = searchAdapter
        }

        binding.editSearch.setOnKeyListener { view, i, keyEvent ->
            if(keyEvent.action==KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER){
                val searchView = view as EditText
                searchImages(searchView.text.toString())
                requireActivity().hideSoftKeyboard()
                searchView.clearFocus()
                searchView.isCursorVisible = false
                true
            } else {
                false
            }
        }
    }

    private fun searchImages(query: String){
        job?.cancel()
        job = lifecycleScope.launch {
            searchViewModel.getImages(query).collectLatest {
                searchAdapter.submitData(it)
            }
        }
    }

    override fun saveClicked(view: ImageView, doc: Response.Document?) {
        view.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.icon_on))
        doc?.thumbnail?.let {
            myBoxViewModel.insert(MyBox(it))
        }
    }

}