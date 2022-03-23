package com.example.imagesearchbox.view

import android.view.KeyEvent
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imagesearchbox.databinding.FragmentSearchBinding
import com.example.imagesearchbox.model.ApiResponse
import com.example.imagesearchbox.model.MyBox
import com.example.imagesearchbox.utils.Utils.hideSoftKeyboard
import com.example.imagesearchbox.utils.Utils.setDateFormat
import com.example.imagesearchbox.view.adapter.SearchAdapter
import com.example.imagesearchbox.viewmodel.MyBoxViewModel
import com.example.imagesearchbox.viewmodel.SearchViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class SearchFragment : BaseFragment<FragmentSearchBinding>(), SearchAdapter.ClickInterface {

    private val searchViewModel: SearchViewModel by viewModel()

    private val myBoxViewModel: MyBoxViewModel by viewModel()

    private lateinit var searchAdapter: SearchAdapter

    private var job: Job? = null

    override fun getViewBinding(): FragmentSearchBinding =  FragmentSearchBinding.inflate(layoutInflater)

    override fun initView() {
        searchAdapter = SearchAdapter(this)

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
                searchAdapter.deleteAllItems()
                searchAdapter.submitData(it)
            }
        }
    }

    override fun saveClicked(doc: ApiResponse.Document?) {
        doc?.thumbnail?.let {
            val date = setDateFormat(Date())
            myBoxViewModel.insert(MyBox(it, date))
        }
    }

}