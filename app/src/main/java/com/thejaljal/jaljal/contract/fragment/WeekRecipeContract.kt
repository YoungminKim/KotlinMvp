package com.thejaljal.jaljal.contract.activity

import com.thejaljal.jaljal.contract.BasePresenter
import com.thejaljal.jaljal.contract.BaseView
import com.thejaljal.jaljal.contract.adapter.WeekRecipeAdapterContract
import com.thejaljal.jaljal.model.WeekRecipe


/**
 * Created by no.1 on 2017-08-30.
 */
interface WeekRecipeContract {

    interface View : BaseView {

    }

    interface Presenter : BasePresenter<View> {

        fun getWeekRecipe()

        fun setWeekRecipeAdapterModel(model: WeekRecipeAdapterContract.Model)
        fun setWeekRecipeAdapterView(view: WeekRecipeAdapterContract.View)

        /**
         * HTTP
         */
        fun handlerResponse(data: Any)
        fun handleError(error: Throwable)
    }

}