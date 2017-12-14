package com.thejaljal.jaljal.view.adapter.viewholder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.facebook.share.widget.LikeView
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.model.Recipe
import kotlinx.android.synthetic.main.item_recipe_book.view.*
import test.ym.kotilnpj.http.UrlInfo

/**
 * Created by no.1 on 2017-11-15.
 */

class RecipeBookViewHolder(val ctx: Context, val view: View): RecyclerView.ViewHolder(view){
    fun onBind(item: Recipe.Data){

        view?.run {
            Glide.with(ctx)
                    .load(UrlInfo.RECIPE_PATH + item.thmbFile)
                    .placeholder(R.drawable.img_default)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade()
                    .into(iv)
            name_tv.text = item.menuName
            content_tv.text = item.simpleInst

            like_view.isEnabled = true
            like_view.setLikeViewStyle(LikeView.Style.BOX_COUNT)
            like_view.setAuxiliaryViewPosition(LikeView.AuxiliaryViewPosition.INLINE)
            like_view.setObjectIdAndType("http://www.thejaljal.com/recipeView.do?seq=" + item.rcipSeq, LikeView.ObjectType.OPEN_GRAPH)

        }
    }

}