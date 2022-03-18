package com.example.imagesearchbox

import android.app.Application
import com.example.imagesearchbox.http.appModules
import com.example.imagesearchbox.http.viewModelModules
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class Application : Application(){
    override fun onCreate() {
        super.onCreate()

        Logger.addLogAdapter(AndroidLogAdapter())
        startKoin {
            androidContext(this@Application)
            modules(appModules)
            modules(viewModelModules)
        }
    }
}