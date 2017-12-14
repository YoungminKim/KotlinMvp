package com.thejaljal.jaljal.view.adapter.viewholder

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.thejaljal.jaljal.model.Recipe
import kotlinx.android.synthetic.main.item_tip.view.*
import test.ym.kotilnpj.http.UrlInfo

/**
 * Created by no.1 on 2017-11-20.
 */

class RecipeTipViewHolder(val ctx: Context, val view: View){

    fun onBind(item: Recipe.Tip){
        Glide.with(ctx)
                .load(UrlInfo.TIP_PATH + item.thmbFile)
                //.load("http://intra.kjsoft.co.kr/~jspark/images/ingredient_img1.png")
                //.load("http://image.thejaljal.com/additionalOrder/1500621284485__3.jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view.iv)
    }
}