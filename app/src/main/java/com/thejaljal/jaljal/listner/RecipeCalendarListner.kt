package com.thejaljal.jaljal.listner

/**
 * Created by no.1 on 2017-12-07.
 */

interface RecipeCalendarListner {
    fun onDeliveryClick(check: Boolean, position: Int)
    fun onCheckModifyStatus(position: Int)
    fun showToast(str: String)
}