package com.thejaljal.jaljal.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.contract.adapter.PremiumItemAdapterContract
import com.thejaljal.jaljal.model.PremiumItem
import com.thejaljal.jaljal.view.adapter.viewholder.PremiumItemViewHolder

/**
 * Created by no.1 on 2017-12-12.
 */

class PremiumItemAdapter(override val ctx: Context) : CommonAdapter(), PremiumItemAdapterContract.View, PremiumItemAdapterContract.Model{

    override var checkFunc: ((Int, Boolean) -> Unit)? = { i : Int, b: Boolean -> checkItem(i, b)}

    override var IsOrder: Boolean = false
    var list = arrayListOf<PremiumItem.Item>()

    override val isChecks: BooleanArray by lazy {
        BooleanArray(list.size)
    }

    override fun addData(data: ArrayList<PremiumItem.Item>) {
        list = data
        setBasicItemCount(list.size)
    }

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

    override fun onCreateBasicViewHolder(parent: ViewGroup, viewType: Int) = PremiumItemViewHolder(ctx, inflater.inflate(R.layout.item_premiumingr, parent,false), IsOrder, checkFunc)

    override fun onBindBasicItemView(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as PremiumItemViewHolder
        list[position]?.let {
            holder.onBind(it, position)
        }
    }


    override fun checkItem(position: Int, isCheck: Boolean) {
        isChecks[position] = isCheck
    }

    override fun getCheckItemList(): ArrayList<PremiumItem.Item>? {
        val checkItemList = arrayListOf<PremiumItem.Item>()
        for (i in 0..isChecks.size -1){
            if(isChecks[i]) checkItemList.add(list[i])
        }

        return checkItemList

    }

}