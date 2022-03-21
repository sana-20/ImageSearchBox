package com.example.imagesearchbox.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.imagesearchbox.utils.Constants
import com.example.imagesearchbox.utils.Utils.isInternetAvailable
import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class CacheInterceptor(context: Context) {

    val onlineInterceptor: Interceptor = object : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val builder = request.newBuilder()
                .addHeader("Authorization", Constants.API_KEY)
                .header("Cache-Control", "public, max-age=300")
                .removeHeader("Pragma")
            Logger.d(builder.build())
            return chain.proceed(builder.build())
        }
    }

    val offlineInterceptor: Interceptor = object : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            var request: Request = chain.request()
            if (!isInternetAvailable(context)) {
                val maxStale = 60 * 60 * 24 * 30
                request = request.newBuilder()
                    .addHeader("Authorization", Constants.API_KEY)
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .removeHeader("Pragma")
                    .build()
            }
            return chain.proceed(request)
        }
    }

}