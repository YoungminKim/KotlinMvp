package com.thejaljal.jaljal.contract.adapter

import android.view.View as AndroidView
import com.thejaljal.jaljal.contract.BaseView

/**
 * Created by no.1 on 2017-12-05.
 */

interface StringDialogApdaterContract {

    interface View:BaseView{
        fun notifyAdapter()
        var onClickFunc: ((Int) -> Unit)?
    }

    interface Model{
        fun addData(list: ArrayList<String>)
        fun getItem(position: Int): String
    }
}