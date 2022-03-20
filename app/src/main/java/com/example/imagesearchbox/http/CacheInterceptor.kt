package com.example.imagesearchbox.http

import com.example.imagesearchbox.utils.Constants.API_KEY
import okhttp3.Interceptor
import okhttp3.Response


class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val cacheTime = 5 * 60 * 60

        val request = chain.request()
        val builder = request.newBuilder()
            .url(request.url)
            .addHeader("Authorization", API_KEY)
            .header("Cache-control", "public, max-age=${cacheTime}")

        return chain.proceed(builder.build())
    }

}