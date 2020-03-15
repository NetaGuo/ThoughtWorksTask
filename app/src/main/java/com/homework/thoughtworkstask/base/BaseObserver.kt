package com.homework.thoughtworkstask.base

import android.widget.Toast
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


abstract class BaseObserver<T>:Observer<T> {


    abstract fun onSuccess(t: T)

    abstract fun onFailure(msg:String)


    override fun onComplete() {
        //request finish
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: T) {
        //validate the Response when need
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        //deal with different errors
        onFailure(e.message.orEmpty())
    }

}