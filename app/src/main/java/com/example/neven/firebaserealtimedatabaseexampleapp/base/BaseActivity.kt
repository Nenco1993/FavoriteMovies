package com.example.neven.firebaserealtimedatabaseexampleapp.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.neven.firebaserealtimedatabaseexampleapp.MyApplication
import com.example.neven.firebaserealtimedatabaseexampleapp.di.components.AppComponent

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies(MyApplication.appComponent)
    }

    abstract fun injectDependencies(appComponent: AppComponent)
}
