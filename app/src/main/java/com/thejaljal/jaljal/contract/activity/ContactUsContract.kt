package com.thejaljal.jaljal.contract.activity

import com.thejaljal.jaljal.contract.BasePresenter
import com.thejaljal.jaljal.contract.BaseView

/**
 * Created by no.1 on 2017-12-04.
 */

interface ContactUsContract{
    interface View: BaseView{
        fun setName(name: String)
        fun writeComplete()
    }

    interface Presenter: BasePresenter<View>{

        fun getUserInfo()
        fun writeContact()

        fun userInfoResponse(data: Any)
        fun writeResponse(data: Any)
        fun handleError(error: Throwable)
    }
}