package com.thejaljal.jaljal.contract.activity

import android.content.Context
import com.thejaljal.jaljal.contract.BasePresenter
import com.thejaljal.jaljal.contract.BaseView

/**
 * Created by no.1 on 2017-09-04.
 */

interface SplashContract{
    interface View : BaseView {
        fun showOneDialog(title: String?, msg: String?, btn: String, msgWhat: Int)
        fun showTwoDialog(title: String?, msg: String?, btn1: String, btn2: String, msgWhat1: Int, msgWhat2: Int)
        fun onNextPage()
    }

    interface Presenter: BasePresenter<View> {
        fun setContext(ctx: Context)
        fun checkService()


        /**
         * HTTP
         */
        fun handlerResponse(data: Any)
        fun handleError(error: Throwable)
    }
}