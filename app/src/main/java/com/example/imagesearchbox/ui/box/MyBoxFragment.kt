package com.example.imagesearchbox.ui.box

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imagesearchbox.databinding.FragmentBoxBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyBoxFragment : Fragment() {

    private val myBoxViewModel: MyBoxViewModel by viewModel()

    private lateinit var binding: FragmentBoxBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBoxBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val myBoxRecyclerAdapter = MyBoxRecyclerAdapter()

        binding.recyclerBox.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = myBoxRecyclerAdapter
        }

        myBoxViewModel.allWords.observe(owner = viewLifecycleOwner) {
            myBoxRecyclerAdapter.updateData(it)
        }
    }

}