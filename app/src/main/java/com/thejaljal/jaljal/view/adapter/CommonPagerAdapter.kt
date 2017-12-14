package com.thejaljal.jaljal.view.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

/**
 * Created by no.1 on 2017-09-05.
 */

abstract class CommonPagerAdapter: PagerAdapter() {
    abstract protected val ctx: Context
    val inflater: LayoutInflater by lazy {
        ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean = false
    override fun getCount(): Int = 0

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        (container as ViewPager).removeView(`object` as View)
    }

    fun setToast(msgint: Int) {
        Toast.makeText(ctx, msgint, Toast.LENGTH_SHORT).show()
    }

    fun setToast(msgStr: String) {
        Toast.makeText(ctx, msgStr, Toast.LENGTH_SHORT).show()
    }
}