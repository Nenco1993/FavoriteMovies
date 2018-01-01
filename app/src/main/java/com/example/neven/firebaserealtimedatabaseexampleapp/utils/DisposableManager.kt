package com.example.neven.firebaserealtimedatabaseexampleapp.utils

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class DisposableManager {

    companion object {
        var instance: DisposableManager = DisposableManager()
    }

    private var compositeDisposable: CompositeDisposable? = null

    fun clear() {
        getCompositeDisposable().clear()
    }

    fun add(disposable: Disposable) {
        getCompositeDisposable().add(disposable)
    }

    private fun getCompositeDisposable(): CompositeDisposable {
        if (compositeDisposable == null || compositeDisposable?.isDisposed!!) {
            compositeDisposable = CompositeDisposable()
        }
        return compositeDisposable!!
    }
}