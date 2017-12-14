package com.thejaljal.jaljal.contract.activity

import android.content.Intent
import com.thejaljal.jaljal.model.Login
import com.thejaljal.jaljal.contract.BasePresenter
import com.thejaljal.jaljal.contract.BaseView


/**
 * Created by no.1 on 2017-08-30.
 */
interface LoginContract{

    interface View : BaseView {
        fun loginSuccess(user: Login)
    }

    interface Presenter : BasePresenter<View> {

        fun login(id:String?, pass: String?, key:String?, type:String)
        fun naverLogin()
        fun facebookLogin()
        fun kakaoLogin()
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent)

        /**
         * HTTP
         */
        fun handlerResponse(data: Any)
        fun handleError(error: Throwable)
    }

}