package com.thejaljal.jaljal.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.contract.adapter.RecipeCaladarAdapterContract
import com.thejaljal.jaljal.model.RecipeCalendar
import com.thejaljal.jaljal.view.adapter.viewholder.RecipeCalendarViewHolder

/**
 * Created by no.1 on 2017-10-12.
 */

class RecipeCalendarAdapter(override val ctx: Context): CommonAdapter(), RecipeCaladarAdapterContract.Model, RecipeCaladarAdapterContract.View{
    override var onDeliveryClick: ((Boolean, Int) -> Unit)? = null
    override var onCheckModifyStatus: ((Int) -> Unit)? = null
    override var showToast: ((String) -> Unit)? = null
    val TAG = javaClass.simpleName

    override var list: ArrayList<RecipeCalendar.CalendarData> = arrayListOf()



    override fun onCreateBasicViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = RecipeCalendarViewHolder(ctx, inflater.inflate(R.layout.item_recipe_calandar, parent, false), onDeliveryClick, onCheckModifyStatus, showToast)

    override fun onBindBasicItemView(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as RecipeCalendarViewHolder
        list[position].let {
            holder.onBind(it, position)
        }
    }

    override fun addList(list: ArrayList<RecipeCalendar.CalendarData>) {
        this.list = list
        setBasicItemCount(list.size)
    }


    override fun notifyAdapter() {
        notifyDataSetChanged()
    }


    override fun getItem(position: Int) = list[position]


    override fun setDeliveryStatus(position: Int, item:  RecipeCalendar.CalendarData?) {
        list[position] = item!!
    }



}