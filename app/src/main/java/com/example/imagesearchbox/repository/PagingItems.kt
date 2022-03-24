package com.example.imagesearchbox.repository

import com.example.imagesearchbox.model.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class PagingItems(private val apiList: List<ApiResponse>) {

    var isEnd = false

    private val _pagingList: MutableList<ApiResponse.Document> = mutableListOf()
    val pagingList: List<ApiResponse.Document>
        get() {
            return _pagingList.sortedByDescending { it.datetime }
        }

    suspend fun callApi() {
        coroutineScope {
            for (api in apiList) {
                val response = withContext(Dispatchers.Default) { api }
                addItems(response.documents)
                checkIsEndPage(response)
            }
        }
    }

    private fun addItems(documents: List<ApiResponse.Document>) {
        _pagingList.addAll(documents)
    }

    private fun checkIsEndPage(response: ApiResponse) {
        if (response.meta.is_end) {
            isEnd = true
        }
    }

}