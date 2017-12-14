package com.thejaljal.jaljal.view.dialog

import android.content.Context
import android.os.Bundle
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.contract.dialog.StringDialogContract
import com.thejaljal.jaljal.contract.dialog.StringDialogPresenter
import com.thejaljal.jaljal.view.adapter.StringDialogAdapter
import kotlinx.android.synthetic.main.dialog_list.*
import test.ym.kotilnpj.utils.DebugUtils

/**
 * Created by no.1 on 2017-12-05.
 */

class StringDialog(val ctx: Context, val list: ArrayList<String>? = null, strs: Array<String>? = null):
        CommonDialog<StringDialogContract.View, StringDialogPresenter>(ctx), StringDialogContract.View{


    interface SelectListner{
        fun selectListner(str: String)
    }

    var mListner: SelectListner? = null
    val TAG = javaClass.simpleName

    var mList = arrayListOf<String>()

    constructor(ctx: Context, list: ArrayList<String>): this(ctx, list, null){
        mList = list
    }
    constructor(ctx: Context, strs: Array<String>): this(ctx, null, strs){

        for(str in strs){
            mList.add(str)
        }
    }


    override fun onCreatePresenter() = StringDialogPresenter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_list)

        DebugUtils.setLog(TAG, "size : " + mList.size)
        out_layer.setOnClickListener({ dismiss() })
        val adapter = StringDialogAdapter(context)
        presenter?.setDialogAdapterModel(adapter)
        presenter?.setDialogAdapterView(adapter)
        presenter?.setList(mList)
        lv.adapter = adapter

    }


    override fun selectStr(str: String) {
        mListner?.selectListner(str)
        dismiss()
    }

    open fun setOnItemClick(listner: SelectListner){
        mListner = listner
    }


}


