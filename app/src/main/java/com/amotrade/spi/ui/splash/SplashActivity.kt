package com.amotrade.spi.ui.splash

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.amotrade.spi.R
import com.amotrade.spi.base.BaseActivity
import com.amotrade.spi.databinding.ActivitySplashBinding
import com.amotrade.spi.utils.launchOnBoardingActivity
import com.amotrade.spi.viewmodelfactory.SplashViewModelFactory
import com.amotrade.spi.viewmodels.SplashViewModel

class SplashActivity : BaseActivity() {

    private lateinit var activitySplashBinding: ActivitySplashBinding

    private lateinit var splashViewModelFactory: SplashViewModelFactory
    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        splashViewModelFactory = SplashViewModelFactory()
        splashViewModel =
            ViewModelProvider(this, splashViewModelFactory).get(SplashViewModel::class.java)

        setObservers()

        intiView()
    }

    private fun setObservers() {
        splashViewModel.getIsRemoteConfigReceived().observe(this, {
            if (it != null && it) {
                launchOnBoardingActivity(this)
                finish()
            }
        })
    }

    private fun intiView() {
        splashViewModel.getConfigSettings()
    }
}