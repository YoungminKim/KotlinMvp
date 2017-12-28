package com.thejaljal.jaljal.contract.adapter

import com.thejaljal.jaljal.contract.BaseView
import com.thejaljal.jaljal.model.Recipe

/**
 * Created by no.1 on 2017-11-15.
 */

interface RecipeBookAdapterContract{
    interface View: BaseView {
        fun notifyAdapter()
    }


    interface Model{
        val list:ArrayList<Recipe.Data>
        fun addList(page:Int, list: ArrayList<Recipe.Data>)
    }
}