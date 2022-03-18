package com.example.imagesearchbox.http

import com.example.imagesearchbox.http.URL.SEARCH_IMAGE_URL
import com.example.imagesearchbox.http.URL.SEARCH_VIDEO_URL
import com.example.imagesearchbox.http.model.Image
import com.example.imagesearchbox.http.model.Video
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET(SEARCH_IMAGE_URL)
    suspend fun getImage(@Query("query") query: String, @Query("page") page: Int, @Query("size") size: Int) : Image

    @GET(SEARCH_VIDEO_URL)
    fun getVideo(@Query("query") query: String, @Query("page") page: Int): Single<Video>

}