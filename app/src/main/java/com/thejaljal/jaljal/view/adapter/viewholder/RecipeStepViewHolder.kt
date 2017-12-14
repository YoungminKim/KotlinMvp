package com.thejaljal.jaljal.view.adapter.viewholder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.model.Recipe
import test.ym.kotilnpj.http.UrlInfo
import kotlinx.android.synthetic.main.item_cook.view.*

/**
 * Created by no.1 on 2017-11-15.
 */

class RecipeStepViewHolder(val ctx: Context, val view: View): RecyclerView.ViewHolder(view){
    fun onBind(item: Recipe.Step){
        Glide.with(ctx)
                .load(UrlInfo.COOK_STEP_PATH + item.thmbFile)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.img_default)
                .crossFade()
                .into(view.iv)
    }
}
