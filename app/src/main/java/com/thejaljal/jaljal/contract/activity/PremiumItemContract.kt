package com.thejaljal.jaljal.contract.activity

import android.content.Intent
import com.thejaljal.jaljal.contract.BasePresenter
import com.thejaljal.jaljal.contract.BaseView
import com.thejaljal.jaljal.contract.adapter.PremiumItemAdapterContract
import com.thejaljal.jaljal.model.Common
import com.thejaljal.jaljal.model.PremiumItem

/**
 * Created by no.1 on 2017-12-11.
 */

interface PremiumItemContract {
    interface View: BaseView{
        fun setIsOrderView(isOrder: Boolean)
    }

    interface Presenter: BasePresenter<View>{
        var adapterView: PremiumItemAdapterContract.View
        var adapterModel: PremiumItemAdapterContract.Model

        fun setExtra(intent: Intent)

        fun getPremiumList()
        fun cancelPremiumItem()
        fun checkModifyStatus()


        fun premiumListResponse(data: PremiumItem)
        fun handleError(error: Throwable)


        fun cancelItemResponse(data: Common)
    }
}