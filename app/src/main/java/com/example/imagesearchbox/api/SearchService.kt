package com.example.imagesearchbox.api

import com.example.imagesearchbox.api.URL.SEARCH_IMAGE_URL
import com.example.imagesearchbox.api.URL.SEARCH_VIDEO_URL
import com.example.imagesearchbox.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET(SEARCH_VIDEO_URL)
    suspend fun getVideo(@Query("query") query: String, @Query("page") page: Int, @Query("size") size: Int): ApiResponse

    @GET(SEARCH_IMAGE_URL)
    suspend fun getImage(@Query("query") query: String, @Query("page") page: Int, @Query("size") size: Int): ApiResponse

}