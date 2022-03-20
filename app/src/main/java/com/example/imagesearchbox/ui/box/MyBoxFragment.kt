package com.example.imagesearchbox.ui.box

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imagesearchbox.databinding.FragmentBoxBinding
import com.example.imagesearchbox.db.MyBox
import com.orhanobut.logger.Logger
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyBoxFragment : Fragment() {

    private val myBoxViewModel: MyBoxViewModel by viewModel()

    private lateinit var adapter : MyBoxAdapter

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

        adapter = MyBoxAdapter()

        binding.recyclerBox.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = adapter
            setHasFixedSize(false)
        }

        myBoxViewModel.allWords.observe(owner = viewLifecycleOwner) { words ->
//            val list = mutableListOf<MyBox>()
//            list.addAll(words)
//            adapter.submitList(list)
            Logger.d(words)

            val list = mutableListOf(MyBox("https://search4.kakaocdn.net/argon/130x130_85_c/KNc9dHxKNvn"), MyBox("https://search4.kakaocdn.net/argon/138x78_80_pr/FV3HMGVzJGX"), MyBox("https://search1.kakaocdn.net/argon/130x130_85_c/1pHlKe6ErMP"))
            adapter.submitList(list)

//            adapter.submitList(null)
//            words.let { adapter.submitList(it) }
        }

    }


}