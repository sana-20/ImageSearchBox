package com.example.imagesearchbox.ui.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.imagesearchbox.http.SearchService
import com.example.imagesearchbox.http.model.Image

class ItemPagingSource(private val service: SearchService, private val query: String) :
    PagingSource<Int, Image.Document>() {

    companion object {
        const val NETWORK_PAGE_SIZE = 20
        const val ITEM_STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image.Document> {
        return try {
            val page = params.key ?: ITEM_STARTING_PAGE_INDEX
            val response = service.getImage(query, page, NETWORK_PAGE_SIZE)
            val isEnd = response.meta.is_end
            val nextKey = if (isEnd) null else page + 1

            LoadResult.Page(
                data = response.documents,
                prevKey = if (page == ITEM_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = nextKey
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Image.Document>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(
                anchorPosition
            )?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
