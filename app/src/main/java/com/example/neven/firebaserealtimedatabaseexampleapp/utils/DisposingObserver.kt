package com.example.neven.firebaserealtimedatabaseexampleapp.utils

import android.support.annotation.CallSuper
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

open class DisposingObserver<T> : Observer<T> {

    @CallSuper
    override fun onSubscribe(d: Disposable?) {
        DisposableManager.instance.add(d!!)
    }

    override fun onComplete() {

    }

    override fun onError(t: Throwable?) {

    }

    override fun onNext(item: T) {

    }
}