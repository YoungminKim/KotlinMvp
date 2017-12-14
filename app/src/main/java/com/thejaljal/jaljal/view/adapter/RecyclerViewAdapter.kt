package com.thejaljal.jaljal.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import test.ym.kotilnpj.utils.DebugUtils

/**
 * Created by no.1 on 2017-09-05.
 */

abstract class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    companion object {
        private val TAG = javaClass.simpleName
        private val TYPE_HEADER = Integer.MIN_VALUE
        private val TYPE_FOOTER = Integer.MIN_VALUE + 1
        private val TYPE_ADAPTEE_OFFSET = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        when (viewType) {

            TYPE_HEADER -> return onCreateHeaderViewHolder(parent, viewType)
            TYPE_FOOTER -> return onCreateFooterViewHolder(parent, viewType)
            TYPE_ADAPTEE_OFFSET -> return onCreateBasicViewHolder(parent, viewType)
            else -> return onCreateBasicViewHolder(parent, viewType)

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0 && holder.itemViewType == TYPE_HEADER) {
            onBindHeaderItemView(holder, position)
        } else if (position == basicItemCount && holder.itemViewType == TYPE_FOOTER) {
            onBindFooterItemView(holder, position)
        } else {
            val basicPosition = position - itemCount + basicItemCount
            onBindBasicItemView(holder, basicPosition)
        }
    }

    override fun getItemCount(): Int {
        var itemCount = basicItemCount
        if (useHeader()) {
            itemCount += 1
        }
        if (useFooter()) {
            itemCount += 1
        }
        return itemCount
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0 && useHeader()) {
            return TYPE_HEADER
        }
        if (position == basicItemCount && useFooter()) {
            return TYPE_FOOTER
        }

        if (getBasicItemType(position) != Integer.MAX_VALUE - TYPE_ADAPTEE_OFFSET) {
            IllegalStateException("HeaderRecyclerViewAdapter offsets your BasicItemType by $TYPE_ADAPTEE_OFFSET.")
        }

        return getBasicItemType(position) + TYPE_ADAPTEE_OFFSET
    }

    /**
     * 헤더 추가시 true
     */
    open fun useHeader() = false
    open fun onCreateHeaderViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? = null
    open fun onBindHeaderItemView(holder: RecyclerView.ViewHolder, position: Int){}

    /**
     * Footer 추가시 true
     */

    open fun useFooter() = false
    open fun onCreateFooterViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? = null
    open fun onBindFooterItemView(holder: RecyclerView.ViewHolder, position: Int){}


    abstract fun onCreateBasicViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    abstract fun onBindBasicItemView(holder: RecyclerView.ViewHolder?, position: Int)


    private var basicItemCount: Int = 0


    fun setBasicItemCount(count: Int){
        basicItemCount = count
    }

    open fun getBasicItemType(position: Int): Int = position


}