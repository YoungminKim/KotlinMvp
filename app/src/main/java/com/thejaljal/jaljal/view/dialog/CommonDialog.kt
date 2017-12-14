package com.thejaljal.jaljal.view.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.thejaljal.jaljal.contract.BasePresenter
import com.thejaljal.jaljal.contract.BaseView
import test.ym.kotilnpj.utils.RecycleUtils

/**
 * Created by no.1 on 2017-12-05.
 */

abstract class CommonDialog<in VIEW : BaseView, P: BasePresenter<VIEW>>: Dialog, BaseView {

    protected var presenter: P? = null
        private set
    abstract fun onCreatePresenter(): P?

    constructor(ctx: Context): super(ctx, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen)
    constructor(ctx: Context, theme: Int) : super(ctx, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen)
    protected constructor(ctx: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener) : super(ctx, cancelable, cancelListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = onCreatePresenter()
        presenter?.attachView(this as VIEW)

        val lpWindow = WindowManager.LayoutParams()
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        lpWindow.dimAmount = 0.8f
        window!!.attributes = lpWindow
    }

    override fun dismiss() {
        super.dismiss()
        presenter?.detachView()
        RecycleUtils.recursiveRecycle(window.decorView)
        System.gc()
    }

    override fun setToast(msgint: Int) {
        if(msgint > -1)Toast.makeText(context, msgint, Toast.LENGTH_SHORT).show()
    }

    override fun setToast(msgStr: String) {
        if(msgStr != null)Toast.makeText(context, msgStr, Toast.LENGTH_SHORT).show()
    }


}