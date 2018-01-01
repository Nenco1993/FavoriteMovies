package com.example.neven.firebaserealtimedatabaseexampleapp.base

interface BasePresenter<T> {

    fun takeView(view: T)
    fun dropView()
    fun unsubscribe()
}