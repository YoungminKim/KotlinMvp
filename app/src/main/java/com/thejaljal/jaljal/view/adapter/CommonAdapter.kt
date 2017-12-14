package com.thejaljal.jaljal.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.Toast
import test.ym.kotilnpj.manager.PreferencesManager

/**
 * Created by no.1 on 2017-09-05.
 */

abstract class CommonAdapter(): RecyclerViewAdapter() {
    abstract val ctx: Context
    val inflater: LayoutInflater by lazy {
        ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    fun setToast(msgint: Int) {
        if(msgint > -1)Toast.makeText(ctx, msgint, Toast.LENGTH_SHORT).show()
    }

    fun setToast(msgStr: String) {
        if(msgStr != null)Toast.makeText(ctx, msgStr, Toast.LENGTH_SHORT).show()
    }


}

