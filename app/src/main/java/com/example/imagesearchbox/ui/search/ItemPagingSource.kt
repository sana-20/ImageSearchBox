package com.example.imagesearchbox.ui.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.imagesearchbox.http.SearchService
import com.example.imagesearchbox.http.model.Response
import com.orhanobut.logger.Logger
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.merge
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ItemPagingSource(private val service: SearchService, private val query: String) :
    PagingSource<Int, Response.Document>() {

    companion object {
        const val NETWORK_PAGE_SIZE = 20
        const val ITEM_STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Response.Document> {
        return try {
            coroutineScope {
                val page = params.key ?: ITEM_STARTING_PAGE_INDEX

                val imageResponse: Response?
                val videoResponse: Response?
                val imageCall = async { service.getImage(query, page, NETWORK_PAGE_SIZE) }
                val videoCall = async { service.getVideo(query, page, NETWORK_PAGE_SIZE) }

                imageResponse = imageCall.await()
                videoResponse = videoCall.await()
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
            Logger.d(e.message.toString())
            LoadResult.Error(e)
        }
    }

    private fun mergeResponse(
        list1: List<Response.Document>,
        list2: List<Response.Document>
    ): MutableList<Response.Document> {
        val mergedList = mutableListOf<Response.Document>()
        mergedList.addAll(list1)
        mergedList.addAll(list2)
        mergedList.sortByDescending {
            it.datetime
        }
        return mergedList
    }


    override fun getRefreshKey(state: PagingState<Int, Response.Document>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(
                anchorPosition
            )?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
