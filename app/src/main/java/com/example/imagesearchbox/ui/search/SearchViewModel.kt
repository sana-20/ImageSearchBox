package com.example.imagesearchbox.ui.search

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.imagesearchbox.http.ApiRepository
import com.example.imagesearchbox.http.model.Image
import com.example.imagesearchbox.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow


class SearchViewModel(private val repository: ApiRepository) : BaseViewModel() {

    suspend fun getImages(query: String) : Flow<PagingData<Image.Document>> {
     return repository.getImagePaging(query).cachedIn(viewModelScope)
    }

}