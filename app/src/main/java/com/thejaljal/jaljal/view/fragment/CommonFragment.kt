package com.thejaljal.jaljal.view.fragment

import android.arch.lifecycle.LifecycleFragment
import android.os.Bundle
import android.widget.Toast
import com.thejaljal.jaljal.contract.BasePresenter
import com.thejaljal.jaljal.contract.BaseView
import test.ym.kotilnpj.utils.RecycleUtils

/**
 * Created by no.1 on 2017-10-10.
 */

abstract class CommonFragment<in VIEW : BaseView, P:BasePresenter<VIEW>> : LifecycleFragment(), BaseView{
    protected var presenter: P? = null
        private set
    abstract fun onCreatePresenter(): P?
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = onCreatePresenter()
        presenter?.attachView(this as VIEW)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
    }
    override fun setToast(msgStr: String) {
        Toast.makeText(context, msgStr, Toast.LENGTH_SHORT).show()
    }

    override fun setToast(msgInt: Int) {
        if(msgInt > -1)Toast.makeText(context, msgInt, Toast.LENGTH_SHORT).show()
    }


}