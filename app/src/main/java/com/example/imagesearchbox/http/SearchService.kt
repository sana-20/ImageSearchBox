package com.example.imagesearchbox.http

import com.example.imagesearchbox.http.URL.SEARCH_IMAGE_URL
import com.example.imagesearchbox.http.URL.SEARCH_VIDEO_URL
import com.example.imagesearchbox.http.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET(SEARCH_VIDEO_URL)
    suspend fun getVideo(@Query("query") query: String, @Query("page") page: Int, @Query("size") size: Int): Response

    @GET(SEARCH_IMAGE_URL)
    suspend fun getImage(@Query("query") query: String, @Query("page") page: Int, @Query("size") size: Int): Response

}