package com.amotrade.spi.utils

import androidx.appcompat.widget.AppCompatEditText

fun isTextEnteredEmpty(editText:AppCompatEditText):Boolean{
    return editText.text.toString().isBlank()
}