package com.thejaljal.jaljal.contract

import test.ym.kotilnpj.utils.DebugUtils

/**
 * Created by no.1 on 2017-09-04.
 */

abstract class AbstractPresenter<VIEW : BaseView> : BasePresenter<VIEW> {

    protected var view: VIEW? = null

    override fun attachView(view: VIEW) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }
}