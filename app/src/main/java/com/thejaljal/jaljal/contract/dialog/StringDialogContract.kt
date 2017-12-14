package com.thejaljal.jaljal.contract.dialog

import com.thejaljal.jaljal.contract.BasePresenter
import com.thejaljal.jaljal.contract.BaseView
import com.thejaljal.jaljal.contract.adapter.StringDialogApdaterContract

/**
 * Created by no.1 on 2017-12-05.
 */

interface StringDialogContract{
    interface View: BaseView {
        fun selectStr(str: String)
    }


    interface Presenter: BasePresenter<View> {
        fun setDialogAdapterView(view: StringDialogApdaterContract.View)
        fun setDialogAdapterModel(model: StringDialogApdaterContract.Model)
        fun setList(arrayList: ArrayList<String>)

    }
}