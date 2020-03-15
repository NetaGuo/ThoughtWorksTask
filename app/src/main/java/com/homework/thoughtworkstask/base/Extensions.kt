package com.homework.thoughtworkstask.base

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

// kotlin method extension
internal inline fun <reified T> MutableLiveData<T>.toLiveData() = this as LiveData<T>

internal fun View.visible() {
    this.visibility = View.VISIBLE
}

internal fun View.gone() {
    this.visibility = View.GONE
}