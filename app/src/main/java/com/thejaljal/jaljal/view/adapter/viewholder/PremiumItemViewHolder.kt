package com.thejaljal.jaljal.view.adapter.viewholder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.thejaljal.jaljal.model.PremiumItem
import kotlinx.android.synthetic.main.item_premiumingr.view.*
import test.ym.kotilnpj.http.UrlInfo

/**
 * Created by no.1 on 2017-12-13.
 */

class PremiumItemViewHolder(val ctx: Context, val view: View, val isOrder: Boolean, val checkFunc: ((Int, Boolean) -> Unit)?):RecyclerView.ViewHolder(view){
    fun onBind(item: PremiumItem.Item, position: Int){

        view.run {
            Glide.with(ctx)
                    .load(UrlInfo.PREMIUM_ITEM_PATH + item.thmbFile)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(iv)

            if(!isOrder){
                cb.setOnClickListener({
                    checkFunc?.invoke(position, cb.isChecked)
                })
            }else{
                cb.visibility = View.GONE
            }
        }

    }

}