package com.thejaljal.jaljal.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.contract.activity.WeekRecipeContract
import com.thejaljal.jaljal.contract.activity.WeekRecipePresenter
import com.thejaljal.jaljal.view.adapter.WeekRecipeAdapter
import com.thejaljal.jaljal.view.fragment.CommonFragment
import kotlinx.android.synthetic.main.fragment_week_recipe.view.*

/**
 * Created by no.1 on 2017-10-10.
 */

class WeekRecipeFragment : CommonFragment<WeekRecipeContract.View, WeekRecipeContract.Presenter>(), WeekRecipeContract.View{

    override fun onCreatePresenter(): WeekRecipeContract.Presenter? = WeekRecipePresenter(context)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v  = inflater?.inflate(R.layout.fragment_week_recipe, container, false) as View
        val adapter = WeekRecipeAdapter(context)
        v.rv.adapter = adapter
        presenter?.setWeekRecipeAdapterModel(adapter)
        presenter?.setWeekRecipeAdapterView(adapter)
        presenter?.getWeekRecipe()
        return v
    }

}
