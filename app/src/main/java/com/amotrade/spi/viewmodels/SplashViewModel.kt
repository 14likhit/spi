package com.amotrade.spi.viewmodels

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

class SplashViewModel() : ViewModel() {

    private val disposables = CompositeDisposable()

    private val isRemoteConfigReceived = MutableLiveData<Boolean>()

    fun getIsRemoteConfigReceived():MutableLiveData<Boolean>{
        return isRemoteConfigReceived
    }

    fun getConfigSettings(){
        val handler = Handler()
        handler.postDelayed({
            //Write whatever to want to do after delay specified (1 sec)
            isRemoteConfigReceived.value = true
        }, 1000)
    }

    override fun onCleared() {
        disposables.clear()
    }

}