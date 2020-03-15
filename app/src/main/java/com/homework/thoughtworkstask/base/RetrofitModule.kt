package com.homework.thoughtworkstask.base

import com.homework.thoughtworkstask.request.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {

    var retrofit:Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_HOST)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    fun <T> create(service: Class<T>?): T {
        return retrofit.create(service)
    }
}