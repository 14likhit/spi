package com.amotrade.spi.ui.onboarding

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.amotrade.spi.R
import com.amotrade.spi.base.BaseActivity
import com.amotrade.spi.databinding.ActivityOnboardingBinding
import com.amotrade.spi.viewmodelfactory.OnboardingViewModelFactory
import com.amotrade.spi.viewmodels.OnboardingViewModel

class OnboardingActivity : BaseActivity() {

    private lateinit var activityOnboardingBinding: ActivityOnboardingBinding

    private lateinit var onboardingViewModelFactory: OnboardingViewModelFactory
    private lateinit var onboardingViewModel: OnboardingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityOnboardingBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_onboarding)

        onboardingViewModelFactory = OnboardingViewModelFactory()

        onboardingViewModel =
            ViewModelProvider(this, onboardingViewModelFactory).get(OnboardingViewModel::class.java)

        initView()
    }

    private fun initView() {

    }
}