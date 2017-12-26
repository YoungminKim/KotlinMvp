package com.thejaljal.jaljal.utils

import java.text.DecimalFormat
import java.util.*

/**
 * Created by no.1 on 2017-12-21.
 */

object CurrencyUnit{
    fun getWon(money: Int) = Currency.getInstance(Locale.KOREA).getSymbol() + DecimalFormat("#,###").format(money)
}