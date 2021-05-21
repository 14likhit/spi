package com.amotrade.spi.ui.splash

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.amotrade.spi.R
import com.amotrade.spi.base.BaseActivity
import com.amotrade.spi.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity() {

    private lateinit var activitySplashBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
    }
}