package com.thejaljal.jaljal.contract.activity

import com.thejaljal.jaljal.contract.BasePresenter
import com.thejaljal.jaljal.contract.BaseView

/**
 * Created by no.1 on 2017-09-04.
 */
interface MainContract{
    interface View : BaseView {

    }

    interface Presenter: BasePresenter<View> {
    }
}