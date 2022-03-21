package com.example.imagesearchbox.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.imagesearchbox.api.SearchService
import com.example.imagesearchbox.model.ApiResponse
import com.example.imagesearchbox.repository.ItemPagingSource.Companion.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow


interface ApiRepository {
    suspend fun getImagePaging(query: String): Flow<PagingData<ApiResponse.Document>>
}

class RepositoryImpl(private val apiService: SearchService) : ApiRepository {

    override suspend fun getImagePaging(query: String): Flow<PagingData<ApiResponse.Document>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                ItemPagingSource(apiService, query)
            }
        ).flow
    }

}
