package com.thejaljal.jaljal.contract.adapter

import com.thejaljal.jaljal.contract.BaseView
import com.thejaljal.jaljal.model.RecipeCalendar

/**
 * Created by no.1 on 2017-09-18.
 */

interface RecipeCaladarAdapterContract {
    interface View: BaseView {
        fun notifyAdapter()
        fun setDeliveryStatus(position: Int, item:  RecipeCalendar.CalendarData?)
        var onDeliveryClick : ((Boolean, Int) -> Unit)?
        var onCheckModifyStatus: ((Int) -> Unit)?
        var showToast: ((String) -> Unit)?
    }


    interface Model{
        var list: ArrayList<RecipeCalendar.CalendarData>
        fun addList(list: ArrayList<RecipeCalendar.CalendarData>)
        fun getItem(position: Int): RecipeCalendar.CalendarData



    }
}