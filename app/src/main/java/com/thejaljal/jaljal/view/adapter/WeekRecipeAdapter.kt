package com.thejaljal.jaljal.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.contract.adapter.WeekRecipeAdapterContract
import com.thejaljal.jaljal.model.Recipe
import com.thejaljal.jaljal.view.adapter.viewholder.WeekRecipeViewHolder
import test.ym.kotilnpj.utils.DebugUtils

/**
 * Created by no.1 on 2017-09-05.
 */

class WeekRecipeAdapter(override val ctx: Context) : CommonAdapter(), WeekRecipeAdapterContract.View, WeekRecipeAdapterContract.Model{


    val TAG = javaClass.simpleName
    lateinit var list: ArrayList<Recipe.Data>


    override fun onCreateBasicViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = WeekRecipeViewHolder(ctx, inflater.inflate(R.layout.item_week_recipe, parent, false))

    override fun onBindBasicItemView(holder: RecyclerView.ViewHolder?, position: Int) {
        DebugUtils.setLog(TAG, "onBindBasicItemView called!!!")
        holder as WeekRecipeViewHolder
        list[position].let { holder.onBind(it) }

    }



    override fun addList(list: ArrayList<Recipe.Data>) {
        this.list = list
        setBasicItemCount(list.size)
    }

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

}