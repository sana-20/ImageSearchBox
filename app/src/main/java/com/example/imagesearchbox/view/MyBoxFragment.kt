package com.example.imagesearchbox.view

import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imagesearchbox.databinding.FragmentBoxBinding
import com.example.imagesearchbox.view.adapter.MyBoxRecyclerAdapter
import com.example.imagesearchbox.viewmodel.MyBoxViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyBoxFragment : BaseFragment<FragmentBoxBinding>(), MyBoxRecyclerAdapter.ClickInterface {

    private val myBoxViewModel: MyBoxViewModel by viewModel()

    override fun getViewBinding(): FragmentBoxBinding =  FragmentBoxBinding.inflate(layoutInflater)

    override fun initView() {
        val myBoxRecyclerAdapter = MyBoxRecyclerAdapter(this)

        binding.recyclerBox.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = myBoxRecyclerAdapter
        }

        binding.btnDeleteAll.setOnClickListener {
            myBoxViewModel.deleteAll()
        }

        myBoxViewModel.allMyBox.observe(owner = viewLifecycleOwner) {
            myBoxRecyclerAdapter.updateData(it)
        }
    }

    override fun saveClicked(id: Int) {
        myBoxViewModel.delete(id)
    }

}