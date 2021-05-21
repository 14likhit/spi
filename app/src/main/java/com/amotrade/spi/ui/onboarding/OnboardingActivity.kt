package com.amotrade.spi.ui.onboarding

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.amotrade.spi.R
import com.amotrade.spi.base.BaseActivity
import com.amotrade.spi.databinding.ActivityOnboardingBinding

class OnboardingActivity : BaseActivity() {

    private lateinit var activityOnboardingBinding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityOnboardingBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_onboarding)
    }
}