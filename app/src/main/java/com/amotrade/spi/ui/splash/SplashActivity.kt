package com.amotrade.spi.ui.splash

import android.os.Bundle
import com.amotrade.spi.R
import com.amotrade.spi.base.BaseActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}