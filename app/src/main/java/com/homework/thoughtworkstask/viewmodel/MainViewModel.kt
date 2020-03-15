package com.homework.thoughtworkstask.viewmodel

import android.app.Application
import android.widget.Toast
import com.homework.thoughtworkstask.base.*
import com.homework.thoughtworkstask.entity.TwitterItem
import com.homework.thoughtworkstask.entity.User
import com.homework.thoughtworkstask.request.RequestInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


internal class MainViewModel(application: Application) : BaseAndroidModel(application) {

    private val _userData by lazy { MutableResLiveData<User>() }
    private val _twitters by lazy { MutableResLiveData<List<TwitterItem>>() }
    var userData = _userData.toLiveData()
    var twitters = _twitters.toLiveData()

    fun getUserData() {

        RetrofitModule.create(RequestInterface::class.java)
            .getUserInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<User>() {

                override fun onSuccess(t: User) {
                    _userData.value = Result.success(t)
                }

                override fun onFailure(msg: String) {
                    //Toast or something
                    Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
                }
            })

    }


    fun getTwittersData() {

        RetrofitModule.create(RequestInterface::class.java)
            .getMomentsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<List<TwitterItem>>() {

                override fun onSuccess(t: List<TwitterItem>) {
                    _twitters.value = Result.success(t)
                }

                override fun onFailure(msg: String) {
                    Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
                }
            })
    }
}
