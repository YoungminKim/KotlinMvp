package com.thejaljal.jaljal.contract.dialog

import android.view.View
import com.thejaljal.jaljal.contract.AbstractPresenter
import com.thejaljal.jaljal.contract.adapter.StringDialogApdaterContract
import com.thejaljal.jaljal.listner.OnItemClickListner
import test.ym.kotilnpj.utils.DebugUtils

/**
 * Created by no.1 on 2017-12-05.
 */

class StringDialogPresenter: AbstractPresenter<StringDialogContract.View>(), StringDialogContract.Presenter, OnItemClickListner{
    val TAG = javaClass.simpleName
    var adapterView: StringDialogApdaterContract.View? = null
    set(value){
        field = value
        field?.onClickFunc = { onItemClick(it) }
    }
    lateinit var adapterModel: StringDialogApdaterContract.Model

    override fun setDialogAdapterView(view: StringDialogApdaterContract.View) {
        adapterView = view
    }

    override fun setDialogAdapterModel(model: StringDialogApdaterContract.Model) {
        adapterModel = model
    }

    override fun setList(arrayList: ArrayList<String>) {
        adapterModel.addData(arrayList)
        adapterView?.notifyAdapter()
    }


    override fun onItemClick(position: Int) {
        DebugUtils.setLog(TAG, "position : " + position)

        adapterModel.getItem(position).let {
            view?.selectStr(it)
        }
    }

}