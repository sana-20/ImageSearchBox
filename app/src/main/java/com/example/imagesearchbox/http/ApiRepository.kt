package com.example.imagesearchbox.http

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.imagesearchbox.http.model.Image
import com.example.imagesearchbox.ui.search.ItemPagingSource
import com.example.imagesearchbox.ui.search.ItemPagingSource.Companion.NETWORK_PAGE_SIZE
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query


interface ApiRepository {
    suspend fun getImagePaging(query: String): Flow<PagingData<Image.Document>>
}

class RepositoryImpl(private val apiService: SearchService) : ApiRepository {

    override suspend fun getImagePaging(query: String): Flow<PagingData<Image.Document>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                ItemPagingSource(apiService, query)
            }
        ).flow
    }

}
