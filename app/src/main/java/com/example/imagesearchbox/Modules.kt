package com.example.imagesearchbox

import android.content.Context
import com.example.imagesearchbox.api.CacheInterceptor
import com.example.imagesearchbox.api.SearchService
import com.example.imagesearchbox.api.URL.BASE_URL
import com.example.imagesearchbox.db.MyBoxDatabase
import com.example.imagesearchbox.repository.ApiRepository
import com.example.imagesearchbox.repository.MyBoxRepository
import com.example.imagesearchbox.repository.RepositoryImpl
import com.example.imagesearchbox.viewmodel.MyBoxViewModel
import com.example.imagesearchbox.viewmodel.SearchViewModel
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModules = module {
    single {
        createWebService<SearchService>(okHttpClient = createHttpClient(androidContext()), baseUrl = BASE_URL)
    }

    factory<ApiRepository> {
        RepositoryImpl(
            apiService = get()
        )
    }
    single{
        getRepository(androidContext())
    }
}

val viewModelModules = module {
    viewModel { SearchViewModel(repository = get()) }
    viewModel { MyBoxViewModel(dataRepository = get()) }
}

fun getRepository(context: Context): MyBoxRepository {
    return MyBoxRepository.getInstance(MyBoxDatabase.getDatabase(context))
}

fun createHttpClient(context: Context): OkHttpClient {
    val cacheSize = (10 * 1024 * 1024).toLong()
    val cache = Cache(context.cacheDir, cacheSize)

    return OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(CacheInterceptor(context).offlineInterceptor)
        .addNetworkInterceptor(CacheInterceptor(context).onlineInterceptor)
        .cache(cache)
        .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, baseUrl: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}