package com.example.imagesearchbox.http

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.imagesearchbox.http.model.Response
import com.example.imagesearchbox.ui.search.ItemPagingSource
import com.example.imagesearchbox.ui.search.ItemPagingSource.Companion.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow


interface ApiRepository {
    suspend fun getImagePaging(query: String): Flow<PagingData<Response.Document>>
}

class RepositoryImpl(private val apiService: SearchService) : ApiRepository {

    override suspend fun getImagePaging(query: String): Flow<PagingData<Response.Document>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                ItemPagingSource(apiService, query)
            }
        ).flow
    }

}
