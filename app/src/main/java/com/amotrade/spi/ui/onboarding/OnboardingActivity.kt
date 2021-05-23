package com.amotrade.spi.ui.onboarding

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.amotrade.spi.R
import com.amotrade.spi.base.BaseActivity
import com.amotrade.spi.databinding.ActivityOnboardingBinding
import com.amotrade.spi.utils.isTextEnteredEmpty
import com.amotrade.spi.utils.launchHomeActivity
import com.amotrade.spi.viewmodelfactory.OnboardingViewModelFactory
import com.amotrade.spi.viewmodels.OnboardingViewModel


class OnboardingActivity : BaseActivity() {

    private lateinit var activityOnboardingBinding: ActivityOnboardingBinding

    private lateinit var onboardingViewModelFactory: OnboardingViewModelFactory
    private lateinit var onboardingViewModel: OnboardingViewModel

    private var panCardNumber = ""
    private var dob = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityOnboardingBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_onboarding)

        onboardingViewModelFactory = OnboardingViewModelFactory()

        onboardingViewModel =
            ViewModelProvider(this, onboardingViewModelFactory).get(OnboardingViewModel::class.java)

        setObservers()

        initView()
    }

    private fun setObservers() {
        onboardingViewModel.getIsValidFormMutableLiveData().observe(this, {
            if (it != null && it) {
                showMessage(getString(R.string.message_success_form_submission))
                launchHomeActivity(this)
                finish()
            }
        })

        onboardingViewModel.getErrorPanNumberMutableLiveData().observe(this, {
            if (it != null && it) {
                setErrorPanNumber()
            }
        })

        onboardingViewModel.getErrorDateOfBirthMutableLiveData().observe(this, {
            if (it != null && it) {
                setErrorDateOfBirth()
            }
        })
    }

    private fun initView() {
        setNextButtonEnable(false)

        setTextWatchers()

        val spannableString = SpannableString(getString(R.string.note_pan_dob))
        val clickableSpan: ClickableSpan = object : ClickableSpan() {

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }

            override fun onClick(textView: View) {
                showMessage(getString(R.string.message_click_learn_more))
            }
        }

        spannableString.setSpan(
            clickableSpan,
            getString(R.string.note_pan_dob).length - 10,
            getString(R.string.note_pan_dob).length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        activityOnboardingBinding.notePanBirthdateTv.text = spannableString
        activityOnboardingBinding.notePanBirthdateTv.movementMethod =
            LinkMovementMethod.getInstance()
        activityOnboardingBinding.notePanBirthdateTv.highlightColor = Color.TRANSPARENT

        activityOnboardingBinding.nextBtn.setOnClickListener {
            setFormDetails()
            onboardingViewModel.validateForm(panCardNumber, dob)
        }

        activityOnboardingBinding.helpNoPanTv.setOnClickListener {
            showMessage(getString(R.string.message_help_no_pan))
            launchHomeActivity(this)
        }
    }

    private fun setFormDetails() {
        panCardNumber = activityOnboardingBinding.panEt.text.toString()
        dob = activityOnboardingBinding.birthdateDateEt.text.toString() + "/" +
                activityOnboardingBinding.birthdateMonthEt.text.toString() + "/" +
                activityOnboardingBinding.birthdateYearEt.text.toString()
    }

    private fun setNextButtonEnable(isButtonEnabled: Boolean) {
        activityOnboardingBinding.nextBtn.isEnabled = isButtonEnabled
        activityOnboardingBinding.nextBtn.backgroundTintList = ColorStateList.valueOf(
            resources.getColor(
                if (isButtonEnabled) {
                    R.color.purple_500
                } else {
                    R.color.purple_200
                }
            )
        )
    }

    private fun setTextWatchers() {
        activityOnboardingBinding.panEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                activityOnboardingBinding.panInputTil.isErrorEnabled = false
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                checkNextButtonToEnable()
            }
        })

        activityOnboardingBinding.birthdateDateEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                activityOnboardingBinding.errorDateOfBirth.text = ""
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                checkNextButtonToEnable()
            }
        })

        activityOnboardingBinding.birthdateMonthEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                activityOnboardingBinding.errorDateOfBirth.text = ""
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                checkNextButtonToEnable()
            }
        })

        activityOnboardingBinding.birthdateYearEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                activityOnboardingBinding.errorDateOfBirth.text = ""
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                checkNextButtonToEnable()
            }
        })


    }

    private fun checkNextButtonToEnable() {
        if (isTextEnteredEmpty(activityOnboardingBinding.panEt) ||
            isTextEnteredEmpty(activityOnboardingBinding.birthdateDateEt) ||
            isTextEnteredEmpty(activityOnboardingBinding.birthdateMonthEt) ||
            isTextEnteredEmpty(activityOnboardingBinding.birthdateYearEt)
        ) {
            setNextButtonEnable(false)
        } else {
            setNextButtonEnable(true)
        }
    }

    private fun setErrorPanNumber() {
        activityOnboardingBinding.panInputTil.error = getString(R.string.error_pan)
    }

    private fun setErrorDateOfBirth() {
        activityOnboardingBinding.errorDateOfBirth.text = getString(R.string.error_birthdate)
    }
}