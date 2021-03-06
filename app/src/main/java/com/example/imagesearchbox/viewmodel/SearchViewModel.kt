package com.example.imagesearchbox.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.imagesearchbox.repository.ApiRepository
import com.example.imagesearchbox.model.ApiResponse
import kotlinx.coroutines.flow.Flow

class SearchViewModel(private val repository: ApiRepository) : ViewModel() {

    suspend fun getImages(query: String): Flow<PagingData<ApiResponse.Document>> {
        return repository.getImagePaging(query).cachedIn(viewModelScope)
    }

}