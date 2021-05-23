package com.amotrade.spi.utils

import android.content.Intent
import com.amotrade.spi.base.BaseActivity
import com.amotrade.spi.ui.onboarding.OnboardingActivity

fun launchOnBoardingActivity(activity: BaseActivity) {
    val intent = Intent(activity, OnboardingActivity::class.java)
    activity.startActivity(intent)
}