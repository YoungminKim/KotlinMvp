package com.thejaljal.jaljal.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.contract.adapter.RecipeBookAdapterContract
import com.thejaljal.jaljal.model.Recipe
import com.thejaljal.jaljal.view.adapter.viewholder.RecipeBookViewHolder

/**
 * Created by no.1 on 2017-11-15.
 */

class RecipeBookAdapter(override val ctx: Context) : CommonAdapter(), RecipeBookAdapterContract.Model, RecipeBookAdapterContract.View{



    lateinit var list: ArrayList<Recipe.Data>

    override fun onCreateBasicViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = RecipeBookViewHolder(ctx, inflater.inflate(R.layout.item_recipe_book, parent, false))

    override fun onBindBasicItemView(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as RecipeBookViewHolder
        list[position].let { holder.onBind(it) }
    }
    override fun notifyAdapter() {
        notifyDataSetChanged()
    }


    override fun addList(list: ArrayList<Recipe.Data>) {
        this.list = list
        setBasicItemCount(list.size)
    }


}