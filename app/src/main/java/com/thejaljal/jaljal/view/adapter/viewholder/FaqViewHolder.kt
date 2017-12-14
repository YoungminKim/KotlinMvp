package com.thejaljal.jaljal.view.adapter.viewholder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.AnimationUtils
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.model.Faq
import kotlinx.android.synthetic.main.item_faq.view.*
import test.ym.kotilnpj.utils.DebugUtils

/**
 * Created by no.1 on 2017-11-29.
 */

class FaqViewHolder(val ctx: Context, val view: View): RecyclerView.ViewHolder(view){



    fun onBind(item: Faq.Board){
        view?.run {
            subject_tv.text = item.boardSj
            content_tv.text = item.boardCn

            group_rl.setOnClickListener({
                slideinDown()
            })
        }

    }

    fun slideinDown(){
        view?.run {
            child_rl.visibility = View.VISIBLE
            child_rl.animation = AnimationUtils.loadAnimation(ctx, R.anim.child_down)
        }

    }


}