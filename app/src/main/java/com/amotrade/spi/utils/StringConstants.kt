package com.amotrade.spi.utils

import java.text.SimpleDateFormat
import java.util.*

//Patten for PAN and date of birth
const val STRING_PATTERN_PAN = "[A-Z]{3}[ABCFGHLJPTF]{1}[A-Z]{1}[0-9]{4}[A-Z]{1}"
val BIRTHDATE_FORMAT = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
