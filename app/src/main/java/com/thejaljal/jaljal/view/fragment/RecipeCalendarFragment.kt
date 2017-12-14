package com.thejaljal.jaljal.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.contract.fragment.RecipeCalendarContract
import com.thejaljal.jaljal.contract.fragment.RecipeCalendarPresenter
import com.thejaljal.jaljal.view.adapter.RecipeCalendarAdapter
import kotlinx.android.synthetic.main.fragment_recipe_calandar.view.*

/**
 * Created by no.1 on 2017-10-10.
 */

class RecipeCalendarFragment : CommonFragment<RecipeCalendarContract.View, RecipeCalendarContract.Presenter>(), RecipeCalendarContract.View {



    override fun onCreatePresenter(): RecipeCalendarContract.Presenter? = RecipeCalendarPresenter(context)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v  = inflater?.inflate(R.layout.fragment_recipe_calandar, container, false) as View
        val adapter = RecipeCalendarAdapter(context)
        v.rv.adapter = adapter
        presenter?.setRecipeCalandarAdapterModel(adapter)
        presenter?.setRecipeCalandarAdapterView(adapter)
        presenter?.getRecipeCalandar()

        return v
    }

    override fun setDeliveryLayout(nextDelivery:String, status: Int) {
        view?.next_delivery_tv?.text = String.format(context.getString(R.string.next_delivery), nextDelivery)
        view?.delivery_rl?.visibility = status
    }

    override fun goPremiumIngr(intent: Intent) {
        startActivity(intent)
    }
}