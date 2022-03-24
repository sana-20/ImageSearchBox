package com.example.imagesearchbox.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.imagesearchbox.api.SearchService
import com.example.imagesearchbox.model.ApiResponse
import kotlinx.coroutines.coroutineScope

class ItemPagingSource(private val service: SearchService, private val query: String) :
    PagingSource<Int, ApiResponse.Document>() {

    companion object {
        const val NETWORK_PAGE_SIZE = 20
        const val ITEM_STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiResponse.Document> {
        return try {
            coroutineScope {
                val page = params.key ?: ITEM_STARTING_PAGE_INDEX

                val pagingItems = PagingItems(
                    arrayListOf(
                        service.getImage(query, page, NETWORK_PAGE_SIZE),
                        service.getVideo(query, page, NETWORK_PAGE_SIZE)
                    )
                )
                pagingItems.callApi()

                val nextKey = if (pagingItems.isEnd) null else page + 1

                LoadResult.Page(
                    data = pagingItems.pagingList,
                    prevKey = if (page == ITEM_STARTING_PAGE_INDEX) null else page - 1,
                    nextKey = nextKey
                )

            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ApiResponse.Document>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(
                anchorPosition
            )?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
