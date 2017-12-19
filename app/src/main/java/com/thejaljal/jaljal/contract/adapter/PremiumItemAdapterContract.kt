package com.thejaljal.jaljal.contract.adapter

import com.thejaljal.jaljal.contract.BaseView
import com.thejaljal.jaljal.model.PremiumItem

/**
 * Created by no.1 on 2017-12-11.
 */

interface PremiumItemAdapterContract {
    interface View: BaseView{
        fun notifyAdapter()

    }

    interface Model{
        var IsOrder: Boolean
        var checkFunc: ((Int, Boolean) -> Unit)?
        val isChecks: BooleanArray
        fun getCheckItemList(): ArrayList<PremiumItem.Item>?
        fun checkItem(position: Int, isCheck: Boolean)
        fun addData(data: ArrayList<PremiumItem.Item>)
    }
}