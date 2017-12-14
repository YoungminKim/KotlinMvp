package com.thejaljal.jaljal.contract.activity

import com.thejaljal.jaljal.contract.BasePresenter
import com.thejaljal.jaljal.contract.BaseView
import com.thejaljal.jaljal.contract.adapter.FaqAdapterContract

/**
 * Created by no.1 on 2017-11-28.
 */

interface FaqContract{
    interface View:BaseView{

    }

    interface Presenter:BasePresenter<View>{

        fun getFaqList()
        fun setFaqAdapterView(view: FaqAdapterContract.View)
        fun setFaqAdapterModel(model: FaqAdapterContract.Model)
        /**
         * HTTP
         */
        fun handlerResponse(data: Any)
        fun handleError(error: Throwable)
    }
}