package com.thejaljal.jaljal.contract.activity

import android.content.Intent
import com.thejaljal.jaljal.contract.BasePresenter
import com.thejaljal.jaljal.contract.BaseView
import com.thejaljal.jaljal.model.OrderPremiumItem
import com.thejaljal.jaljal.model.PremiumDate
import com.thejaljal.jaljal.model.UserInfo

/**
 * Created by no.1 on 2017-12-20.
 */

interface OrderPremiumItemContract{
    interface View: BaseView{
        fun setPlanPrice(plan: String, price:Int)
        fun setTotalPrice(price: Int)
        fun selectDate(str: String?)
        fun showView()
        fun hideView()

    }

    interface Presenter: BasePresenter<View>{
        fun setExtras(intent: Intent)
        fun setSelectDate(position: Int)
        fun setSelectPosition(position: Int)

        fun getDataStringArr(): ArrayList<String>

        fun getUserInfo()
        fun getDate()
        fun orderItems()
        fun userInfoHandler(response: UserInfo)
        fun dateHandler(response: PremiumDate)
        fun orderHandler(response: OrderPremiumItem)
        fun handleError(error: Throwable)

    }
}