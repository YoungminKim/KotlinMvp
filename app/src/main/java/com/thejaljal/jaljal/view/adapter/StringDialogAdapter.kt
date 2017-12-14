package com.thejaljal.jaljal.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.contract.adapter.StringDialogApdaterContract
import com.thejaljal.jaljal.view.adapter.viewholder.StringDialogViewHolder
import test.ym.kotilnpj.utils.DebugUtils

/**
 * Created by no.1 on 2017-12-05.
 */

class StringDialogAdapter(override val ctx: Context): CommonAdapter(), StringDialogApdaterContract.View, StringDialogApdaterContract.Model{
    override var onClickFunc: ((Int) -> Unit)? = null

    val TAG = javaClass.simpleName

    lateinit var list: ArrayList<String>
    override fun onCreateBasicViewHolder(parent: ViewGroup, viewType: Int) = StringDialogViewHolder(inflater.inflate(R.layout.item_string_dialog, parent, false), onClickFunc)

    override fun onBindBasicItemView(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as StringDialogViewHolder
        list[position].let {
            holder.onBind(it, position)
        }

    }


    override fun addData(list: ArrayList<String>) {
        this.list = list
        DebugUtils.setLog(TAG, "size : " + this.list.size)
        for (i in list){
            DebugUtils.setLog(TAG, "item : " + i)
        }
        setBasicItemCount(list.size)
    }

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }



    override fun getItem(position: Int) = list[position]


}