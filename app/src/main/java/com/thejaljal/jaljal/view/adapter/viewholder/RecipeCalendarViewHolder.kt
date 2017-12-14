package com.thejaljal.jaljal.view.adapter.viewholder

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.model.RecipeCalendar
import com.thejaljal.jaljal.view.activity.RecipeActivity

import kotlinx.android.synthetic.main.item_recipe_calandar.view.*
import kotlinx.android.synthetic.main.item_calendar_recipe_name.view.*
import test.ym.kotilnpj.http.UrlInfo

/**
 * Created by no.1 on 2017-10-13.
 */

class RecipeCalendarViewHolder(val ctx: Context, val view: View, val onDeliveryClick: ((Boolean, Int) -> Unit)?, val onCheckModifyStatus: ((Int) -> Unit)?, val showToast: ((String) -> Unit)?): RecyclerView.ViewHolder(view){

    fun onBind(item: RecipeCalendar.CalendarData, position: Int){
        view?.run {
            if(item.dispYn.equals("P")){
                ll.visibility = View.GONE
                iv.visibility = View.VISIBLE
            }else{
                ll.visibility = View.VISIBLE
                iv.visibility = View.GONE

                cb.isChecked = item.ordrStatus.equals("O")
                cb.setOnClickListener({
                    onDeliveryClick?.invoke(cb.isChecked, position)
                })

                if(cb.isChecked){
                    status_tv.text = "배송대기중"
                }

                else status_tv.text = "건너뛰기"

                add_ingr_ll.setOnClickListener({
                    if(cb.isChecked){
                        onCheckModifyStatus?.invoke(position)
                    }else{
                        showToast?.invoke("프리미엄 재료를 추가할수 없습니다.")
                    }

                })

                if(recip_ll.childCount == 0){
                    for (recipe in item.recipes){
                        val childView  = View.inflate(ctx, R.layout.item_calendar_recipe_name, null)
                        childView?.run{
                            recip_name_tv.text = recipe.menuName

                            Glide.with(ctx)
                                    .load(UrlInfo.RECIPE_PATH + recipe.mainImgFile!!)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .placeholder(R.drawable.img_default)
                                    .into(recipe_iv)

                            recipe_iv.setOnClickListener({
                                val i = Intent(ctx, RecipeActivity::class.java)
                                i.putExtra("seq", recipe.rcipSeq)
                                i.putExtra("menu", recipe.menuName)
                                ctx.startActivity(i)

                            })
                        }


                        recip_ll.addView(childView)
                    }
                }


            }

        }
    }
}