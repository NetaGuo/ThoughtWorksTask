package com.homework.thoughtworkstask.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect


typealias MutableResLiveData<T> = MutableLiveData<Result<T>>

internal abstract class BaseViewModel : ViewModel() {
    internal suspend inline fun <reified T> Flow<T>.receive(liveData: MutableResLiveData<T>) {
        catch {
            liveData.value = Result.failure(it)
        }.collect {
            liveData.value = Result.success(it)
        }
    }
}

internal abstract class BaseAndroidModel(application: Application) : AndroidViewModel(application) {
    protected val context get() = getApplication<ThoughtApp>().applicationContext

    internal suspend inline fun <reified T> Flow<T>.receive(liveData: MutableLiveData<Result<T>>) {
        catch {
            liveData.value = Result.failure(it)
        }.collect {
            liveData.value = Result.success(it)
        }
    }
}
