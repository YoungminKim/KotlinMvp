package com.thejaljal.jaljal.view.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_string_dialog.view.*
import test.ym.kotilnpj.utils.DebugUtils

/**
 * Created by no.1 on 2017-12-05.
 */

class StringDialogViewHolder(val view: View, val listnerFunc: ((Int) -> Unit)?): RecyclerView.ViewHolder(view){

     fun onBind(item: String, position: Int){
         DebugUtils.setLog("StringDialogViewHolder", "item : " + item)
         view?.run {
             tv.text = item
             tv.setOnClickListener { listnerFunc?.invoke(position) }
         }
    }
}