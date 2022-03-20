package com.example.imagesearchbox.http

import android.content.Context
import com.example.imagesearchbox.db.MyBoxDatabase
import com.example.imagesearchbox.db.MyBoxRepository
import com.example.imagesearchbox.http.URL.BASE_URL
import com.example.imagesearchbox.ui.box.MyBoxViewModel
import com.example.imagesearchbox.ui.search.SearchViewModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
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


fun createHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(CacheInterceptor()).build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, baseUrl: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}