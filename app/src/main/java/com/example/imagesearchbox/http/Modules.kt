package com.example.imagesearchbox.http

import com.example.imagesearchbox.http.URL.BASE_URL
import com.example.imagesearchbox.ui.search.SearchViewModel
import com.example.imagesearchbox.utils.Constants.API_KEY
import com.google.gson.GsonBuilder
import com.orhanobut.logger.Logger
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val appModules = module {
    single {
        createWebService<SearchService>(okHttpClient = createHttpClient(), baseUrl = BASE_URL)
    }

    factory<ApiRepository> {
        RepositoryImpl(
            apiService = get()
        )
    }
}


val viewModelModules = module {
    viewModel { SearchViewModel(repository = get()) }
}


fun createHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.connectTimeout(10, TimeUnit.SECONDS)
    client.readTimeout(10, TimeUnit.SECONDS)
    client.writeTimeout(10, TimeUnit.SECONDS)
    return client.addInterceptor {
        val url = it.request().url.toString()
        Logger.d(url)
        val request = it.request().newBuilder()
            .addHeader("Authorization", API_KEY)
            .url(url)
            .build()
        return@addInterceptor it.proceed(request)
    }.build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, baseUrl: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}