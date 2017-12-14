package com.thejaljal.jaljal.contract.fragment

import android.content.Intent
import com.thejaljal.jaljal.contract.BasePresenter
import com.thejaljal.jaljal.contract.BaseView
import com.thejaljal.jaljal.contract.adapter.RecipeCaladarAdapterContract
import com.thejaljal.jaljal.model.Common
import com.thejaljal.jaljal.model.RecipeCalendar

/**
 * Created by no.1 on 2017-10-11.
 */
 interface RecipeCalendarContract {
    interface View: BaseView{
        fun setDeliveryLayout(nextDelivery:String, status: Int)
        fun goPremiumIngr(intent: Intent)

    }
    interface Presenter: BasePresenter<View>{

        fun setRecipeCalandarAdapterModel(model: RecipeCaladarAdapterContract.Model)
        fun setRecipeCalandarAdapterView(view: RecipeCaladarAdapterContract.View)

        fun getRecipeCalandar()
        fun setUpdateIntent(intent: Intent):Intent

        fun orderStatus()

        fun updateDeliveryStatus(item: RecipeCalendar.CalendarData)
        fun checkModifyStatus(item: RecipeCalendar.CalendarData)


        /**
         * HTTP
         */
        fun handlerResponse(data: Any)
        fun handleError(error: Throwable)


        fun checkStatusResponse(data: Common)
        fun deliveryStatusResponse(data: Common)


    }
}