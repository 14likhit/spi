package com.amotrade.spi.viewmodels

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

class OnboardingViewModel() : ViewModel() {

    private val disposables = CompositeDisposable()


    override fun onCleared() {
        disposables.clear()
    }

}