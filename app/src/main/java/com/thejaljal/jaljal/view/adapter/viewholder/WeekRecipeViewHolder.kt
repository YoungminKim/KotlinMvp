package com.thejaljal.jaljal.view.adapter.viewholder

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.thejaljal.jaljal.model.Recipe
import com.thejaljal.jaljal.view.activity.RecipeActivity

import kotlinx.android.synthetic.main.item_week_recipe.view.*
import test.ym.kotilnpj.http.UrlInfo
import test.ym.kotilnpj.utils.DebugUtils

/**
 * Created by no.1 on 2017-09-05.
 */

class WeekRecipeViewHolder(val ctx: Context, val view: View): RecyclerView.ViewHolder(view) {

    fun onBind(item: Recipe.Data){
        DebugUtils.setLog("WeekRecipeViewHolder", "onBind called!!!")
        view?.run {
            rl.setOnClickListener({
                val i = Intent(ctx, RecipeActivity::class.java)
                i.putExtra("seq", item.rcipSeq)
                i.putExtra("menu", item.menuName)
                ctx.startActivity(i)
            })

            subject_tv.text = item?.menuName
            Glide.with(ctx)
                    .load(UrlInfo.RECIPE_PATH + item.thmbFile)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade()
                    .into(iv)
            content_tv.text = item.simpleInst
        }

    }




}