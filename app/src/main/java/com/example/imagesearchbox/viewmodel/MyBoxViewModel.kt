package com.example.imagesearchbox.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.imagesearchbox.model.MyBox
import com.example.imagesearchbox.repository.MyBoxRepository
import kotlinx.coroutines.launch


class MyBoxViewModel(private val dataRepository: MyBoxRepository) : ViewModel() {

    val allMyBox: LiveData<List<MyBox>> = dataRepository.allMyBox.asLiveData()

    fun insert(myBox: MyBox) = viewModelScope.launch {
        dataRepository.insert(myBox)
    }
}