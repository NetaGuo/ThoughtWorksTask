package com.homework.thoughtworkstask.base

import android.app.Application
import android.content.res.Configuration
import com.homework.thoughtworkstask.di.viewModelDi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class ThoughtApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        //can use auto-register to init 3-party
    }

    private fun initKoin(){
        startKoin {
            androidContext(this@ThoughtApp)
            modules(viewModelDi)
        }
    }

    //deal with language and so on
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}