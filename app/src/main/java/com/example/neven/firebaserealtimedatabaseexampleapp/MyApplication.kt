package com.example.neven.firebaserealtimedatabaseexampleapp

import android.app.Application
import com.example.neven.firebaserealtimedatabaseexampleapp.di.components.AppComponent
import com.example.neven.firebaserealtimedatabaseexampleapp.di.components.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary


class MyApplication : Application() {

    companion object {
        lateinit var instance: MyApplication
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
        instance = this
        appComponent = DaggerAppComponent.create()
    }


}