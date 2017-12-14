package com.thejaljal.jaljal.contract.adapter

import com.thejaljal.jaljal.contract.BaseView
import com.thejaljal.jaljal.model.Recipe


/**
 * Created by no.1 on 2017-09-18.
 */

interface WeekRecipeAdapterContract {
    interface View: BaseView{
        fun notifyAdapter()
    }


    interface Model{
        fun addList(list: ArrayList<Recipe.Data>)
    }
}