package com.thejaljal.jaljal.model

/**
 * Created by no.1 on 2017-12-20.
 */

data class PremiumDate(val result:Boolean?, val message:String?, val data:Data?){
    data class Data(val dateList: ArrayList<Date>)
    data class Date(
            val predictDate: String?,
            val predictWeek: Int?,
            val predictWeekString: String?
    )
}