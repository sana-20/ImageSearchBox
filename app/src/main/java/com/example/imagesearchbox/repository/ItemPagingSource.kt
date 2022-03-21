package com.example.imagesearchbox.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.imagesearchbox.api.SearchService
import com.example.imagesearchbox.model.ApiResponse
import com.orhanobut.logger.Logger
import kotlinx.coroutines.async
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

                val imageResponse: ApiResponse?
                val videoResponse: ApiResponse?
                val imageCall = async { service.getImage(query, page, NETWORK_PAGE_SIZE) }
                val videoCall = async { service.getVideo(query, page, NETWORK_PAGE_SIZE) }

                imageResponse = imageCall.await()
                videoResponse = videoCall.await()

                Logger.d(imageResponse)

                val mergedList = mergeResponse(imageResponse.documents, videoResponse.documents)

                val isEnd = imageResponse.meta.is_end && videoResponse.meta.is_end
                val nextKey = if (isEnd) null else page + 1

                LoadResult.Page(
                    data = mergedList,
                    prevKey = if (page == ITEM_STARTING_PAGE_INDEX) null else page - 1,
                    nextKey = nextKey
                )

            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun mergeResponse(
        list1: List<ApiResponse.Document>,
        list2: List<ApiResponse.Document>
    ): MutableList<ApiResponse.Document> {
        val mergedList = mutableListOf<ApiResponse.Document>()
        mergedList.addAll(list1)
        mergedList.addAll(list2)
        mergedList.sortByDescending {
            it.datetime
        }
        return mergedList
    }


    override fun getRefreshKey(state: PagingState<Int, ApiResponse.Document>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(
                anchorPosition
            )?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
