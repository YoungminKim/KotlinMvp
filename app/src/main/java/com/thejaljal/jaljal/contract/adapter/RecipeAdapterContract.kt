package com.thejaljal.jaljal.contract.adapter

import com.thejaljal.jaljal.contract.BaseView
import com.thejaljal.jaljal.model.Recipe

/**
 * Created by no.1 on 2017-10-11.
 */

interface RecipeAdapterContract{
    interface View: BaseView{
        fun notifyAdapter()
    }

    interface Model{
        fun addData(data: Recipe.Data)
    }
}