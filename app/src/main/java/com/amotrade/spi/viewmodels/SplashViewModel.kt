package com.amotrade.spi.viewmodels

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

class SplashViewModel() : ViewModel() {

    private val disposables = CompositeDisposable()


    override fun onCleared() {
        disposables.clear()
    }

}