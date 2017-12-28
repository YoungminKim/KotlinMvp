package com.thejaljal.jaljal.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.DebugUtils
import android.view.ViewGroup
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.contract.adapter.FaqAdapterContract
import com.thejaljal.jaljal.model.Faq
import com.thejaljal.jaljal.view.adapter.viewholder.FaqViewHolder
import kotlinx.android.synthetic.main.item_faq.view.*

/**
 * Created by no.1 on 2017-11-29.
 */

class FaqAdapter(override val ctx: Context) : CommonAdapter(), FaqAdapterContract.View, FaqAdapterContract.Model{
    private val TAG = javaClass.simpleName
    override val list: ArrayList<Faq.Board> = arrayListOf()

    override fun onCreateBasicViewHolder(parent: ViewGroup, viewType: Int) = FaqViewHolder(ctx, inflater.inflate(R.layout.item_faq, parent, false))

    override fun onBindBasicItemView(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as FaqViewHolder
        list[position].let { holder.onBind(it) }

    }

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

    override fun addData(data: ArrayList<Faq.Board>) {

        list.addAll(data)

        setBasicItemCount(list.size)
    }


}