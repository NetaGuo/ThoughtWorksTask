package com.homework.thoughtworkstask.request

import com.homework.thoughtworkstask.entity.TwitterItem
import com.homework.thoughtworkstask.entity.User
import io.reactivex.Observable
import retrofit2.http.GET


interface RequestInterface {

    @GET("user/jsmith/tweets")
    fun getMomentsList() : Observable<List<TwitterItem>>

    @GET("user/jsmith")
    fun getUserInfo() : Observable<User>

}