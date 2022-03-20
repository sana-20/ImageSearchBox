package com.example.imagesearchbox.ui.box

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.imagesearchbox.db.MyBox
import com.example.imagesearchbox.db.MyBoxRepository
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch


class MyBoxViewModel(private val dataRepository: MyBoxRepository) : ViewModel() {

    val allWords: LiveData<List<MyBox>> = dataRepository.allMybox.asLiveData()

    fun insert(myBox: MyBox) = viewModelScope.launch {
        dataRepository.insert(myBox)
    }
}