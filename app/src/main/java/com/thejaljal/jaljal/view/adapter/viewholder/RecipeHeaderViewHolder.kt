package com.thejaljal.jaljal.view.adapter.viewholder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.facebook.share.widget.LikeView
import com.thejaljal.jaljal.model.Recipe
import com.thejaljal.jaljal.view.adapter.RecipeTipAdapter
import kotlinx.android.synthetic.main.header_recipe.view.*
import test.ym.kotilnpj.utils.DebugUtils

/**
 * Created by no.1 on 2017-11-16.
 */

class RecipeHeaderViewHolder(val ctx: Context, val view: View): RecyclerView.ViewHolder(view){
    private val TAG = javaClass.simpleName


    fun onBind(item: Recipe.Data){
        view?.run {
            like_view.isEnabled = true
            like_view.setLikeViewStyle(LikeView.Style.BOX_COUNT)
            like_view.setAuxiliaryViewPosition(LikeView.AuxiliaryViewPosition.INLINE)
            like_view.setObjectIdAndType("http://www.thejaljal.com/recipeView.do?seq=" + item.rcipSeq, LikeView.ObjectType.OPEN_GRAPH)

            content_tv.text = item.detailInst



            DebugUtils.setLog(TAG, "size : " + item.tipsList.size)
            if(item.tipsList?.size > 0){
                tip_ll.visibility = View.VISIBLE
                val tipAdapter = RecipeTipAdapter(ctx, item.tipsList)
                tip_vp.adapter = tipAdapter
            }
        }

    }
}