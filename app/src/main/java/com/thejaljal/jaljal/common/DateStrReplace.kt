package com.thejaljal.jaljal.common

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by no.1 on 2017-12-06.
 */


object DateStrReplace {
    private val TAG = javaClass.simpleName

    val WEEKS_KR = arrayOf("일", "월", "화", "수", "목", "금", "토")
    fun setDateKorean(str: String): String {
        var result: String = ""
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        try {
            val date = sdf.parse(str)
            val cal = Calendar.getInstance(Locale.KOREA)
            cal.time = date
            result = (cal.get(Calendar.MONTH) + 1).toString() + "월 " + cal.get(Calendar.DATE) + "일 " + WEEKS_KR[cal.get(Calendar.DAY_OF_WEEK) - 1] + "요일"

        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return result
    }

    fun setDateKoreanYMDW(str: String?): String? {
        if (str == null) return ""
        var result: String? = null
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        try {
            val date = sdf.parse(str)
            val cal = Calendar.getInstance(Locale.KOREA)
            cal.time = date
            result = cal.get(Calendar.YEAR).toString() + "년 " + (cal.get(Calendar.MONTH) + 1) + "월 " + cal.get(Calendar.DATE) + "일 " + WEEKS_KR[cal.get(Calendar.DAY_OF_WEEK) - 1] + "요일"

        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return result
    }
}
