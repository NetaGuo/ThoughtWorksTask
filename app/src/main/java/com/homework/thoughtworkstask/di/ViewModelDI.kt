package com.homework.thoughtworkstask.di

import com.homework.thoughtworkstask.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelDi = module(override = true) {
    viewModel { MainViewModel(androidApplication()) }
}