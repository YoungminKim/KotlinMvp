package com.thejaljal.jaljal.view.adapter

import android.content.Context
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.model.Recipe
import com.thejaljal.jaljal.view.adapter.viewholder.RecipeTipViewHolder

/**
 * Created by no.1 on 2017-11-20.
 */

class RecipeTipAdapter(override val ctx: Context, val list: ArrayList<Recipe.Tip>) : CommonPagerAdapter(){

    override fun getCount() = list.size

    override fun isViewFromObject(view: View?, `object`: Any?) = view === `object` as View

    override fun getItemPosition(`object`: Any?) = super.getItemPosition(`object`)

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        (container as ViewPager).removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        var v: View? = null
        val holder: RecipeTipViewHolder
        if(v == null){
            v = inflater.inflate(R.layout.item_tip, container, false)
            holder = RecipeTipViewHolder(ctx, v)
            v.tag = holder
        }else{
            holder = v.tag as RecipeTipViewHolder
        }

        list[position]?.let { holder.onBind(it) }


        container?.addView(v)
        return v!!
    }



}