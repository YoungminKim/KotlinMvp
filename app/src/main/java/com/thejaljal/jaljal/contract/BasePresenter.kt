package com.thejaljal.jaljal.contract

/**
 * Created by no.1 on 2017-09-04.
 */

interface BasePresenter<in VIEW : BaseView> {

    /**
     * View Attach.
     */
    fun attachView(view: VIEW)

    /**
     * View detach
     */
    fun detachView()



}