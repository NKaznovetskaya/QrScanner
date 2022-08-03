package asta.mobi.qrscanner_native.di

import asta.mobi.qrscanner_native.ui.main.MainViewModel
import asta.mobi.qrscanner_native.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { MainViewModel() }
}