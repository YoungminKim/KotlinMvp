package com.thejaljal.jaljal.utils

import java.util.regex.Pattern

/**
 * Created by no.1 on 2017-09-01.
 */

object CheckPatternUtils {

    //이메일 정규식
    fun checkEmail(value: String): Boolean {
        val str = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$"
        val pattern = Pattern.compile(str)
        return pattern.matcher(value).matches()
    }

    // 한글 정규식
    fun checkKor(value: String): Boolean {
        val str = "^[ㄱ-ㅣ가-힣]*$"
        val pattern = Pattern.compile(str)
        return pattern.matcher(value).matches()
    }

    //핸드폰 정규식
    fun checkPhone(value: String): Boolean {
        val str = "^01(?:0|1[6-9])(?:\\d{3}|\\d{4})\\d{4}$"
        val pattern = Pattern.compile(str)
        return pattern.matcher(value).matches()
    }

    fun removeRemark(str: String): String {
        return str.replace("<!--?[a-zA-Z0-9_+-.,!@#$%^&*();{}:\\/|<>\\s]*?-->?".toRegex(), "")
    }
}
