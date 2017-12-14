package com.thejaljal.jaljal.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.contract.adapter.RecipeAdapterContract
import com.thejaljal.jaljal.model.Recipe
import com.thejaljal.jaljal.view.adapter.viewholder.RecipeHeaderViewHolder
import com.thejaljal.jaljal.view.adapter.viewholder.RecipeStepViewHolder
import test.ym.kotilnpj.utils.DebugUtils

/**
 * Created by no.1 on 2017-11-15.
 */

class RecipeAdapter(override val ctx: Context): CommonAdapter(), RecipeAdapterContract.Model, RecipeAdapterContract.View{
    val TAG = javaClass.simpleName

    lateinit var list: ArrayList<Recipe.Step>
    var data: Recipe.Data? = null


    override fun addData(data: Recipe.Data) {
        this.data = data
        this.list = data.cookingStepList
        setBasicItemCount(list.size)
    }

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }



    override fun onCreateBasicViewHolder(parent: ViewGroup, viewType: Int) = RecipeStepViewHolder(ctx, inflater.inflate(R.layout.item_cook, parent, false))

    override fun onBindBasicItemView(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as RecipeStepViewHolder
        list[position]?.let { holder.onBind(it) }
    }

    override fun useHeader() = true

    override fun onCreateHeaderViewHolder(parent: ViewGroup, viewType: Int) = RecipeHeaderViewHolder(ctx, inflater.inflate(R.layout.header_recipe, parent, false))

    override fun onBindHeaderItemView(holder: RecyclerView.ViewHolder, position: Int) {
        holder as RecipeHeaderViewHolder
        data?.let { holder.onBind(data!!) }


    }

}