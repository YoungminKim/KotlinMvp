package com.thejaljal.jaljal.view.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.thejaljal.jaljal.adapter.WeekRecipeFragment
import com.thejaljal.jaljal.view.fragment.RecipeBookFragment
import com.thejaljal.jaljal.view.fragment.RecipeCalendarFragment

/**
 * Created by no.1 on 2017-09-05.
 */

class MainAdapter(val ctx: Context, fm: FragmentManager?) : FragmentPagerAdapter(fm) {


    companion object {
        private val PAGE_MAX = 3

    }

    override fun getItem(position: Int): Fragment? {

        when(position){
            0 -> return WeekRecipeFragment()
            1 -> return RecipeCalendarFragment()
            2 -> return RecipeBookFragment()
            else -> return null
        }

    }



    override fun getCount() = PAGE_MAX


}

