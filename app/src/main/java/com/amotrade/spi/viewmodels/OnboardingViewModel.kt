package com.amotrade.spi.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amotrade.spi.utils.BIRTHDATE_FORMAT
import com.amotrade.spi.utils.STRING_PATTERN_PAN
import io.reactivex.disposables.CompositeDisposable
import java.text.ParseException
import java.util.regex.Pattern

class OnboardingViewModel() : ViewModel() {

    private val disposables = CompositeDisposable()
    private val errorPanNumberMutableLiveData = MutableLiveData<Boolean>()
    private val errorDateOfBirthMutableLiveData = MutableLiveData<Boolean>()
    private val isValidFormMutableLiveData = MutableLiveData<Boolean>()

    fun getErrorPanNumberMutableLiveData(): MutableLiveData<Boolean> {
        return errorPanNumberMutableLiveData
    }

    fun getErrorDateOfBirthMutableLiveData(): MutableLiveData<Boolean> {
        return errorDateOfBirthMutableLiveData
    }

    fun getIsValidFormMutableLiveData(): MutableLiveData<Boolean> {
        return isValidFormMutableLiveData
    }

    private fun isValidPanNumber(panNumber: String): Boolean {
        val pattern = Pattern.compile(STRING_PATTERN_PAN)
        val matcher = pattern.matcher(panNumber)
        return matcher.matches()
    }

    private fun isValidDateOfBirth(dateOfBirth: String): Boolean {
        val dateArray = dateOfBirth.split("/")
        val date: Int = dateArray[0].toInt()
        val month: Int = dateArray[1].toInt()
        val year: Int = dateArray[2].toInt()
        //Added basic check for date not greater than 31
        //month not greater than 12
        //year between 1960 and 2011
        if (date > 31) {
            return false
        } else if (month > 12) {
            return false
        } else if (year < 1960 && year > 2011) {
            return false
        } else {
            return try {
                val date = BIRTHDATE_FORMAT.parse(dateOfBirth)
                true
            } catch (e: ParseException) {
                false
            }
        }
    }

    fun validateForm(panNumber: String, dateOfBirth: String) {
        if (isValidPanNumber(panNumber) && isValidDateOfBirth(dateOfBirth)) {
            isValidFormMutableLiveData.value = true
        } else if (!isValidPanNumber(panNumber)) {
            errorPanNumberMutableLiveData.value = true
        } else {
            errorDateOfBirthMutableLiveData.value = true
        }
    }

    override fun onCleared() {
        disposables.clear()
    }

}