package com.thejaljal.jaljal.view.activity

import android.os.Bundle
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.contract.activity.RecipeContract
import com.thejaljal.jaljal.contract.activity.RecipePresenter
import com.thejaljal.jaljal.view.adapter.RecipeAdapter

import kotlinx.android.synthetic.main.activity_recipe.*

/**
 * Created by no.1 on 2017-10-16.
 */

class RecipeActivity: CommonActivity<RecipeContract.View, RecipePresenter>(), RecipeContract.View{
    override fun onCreatePresenter(): RecipePresenter = RecipePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        setTitlebarView(sub_ll)
        setTitleTv(intent.getStringExtra("menu"))

        val adapter = RecipeAdapter(this)
        rv.adapter = adapter

        presenter?.run{
            setRecipeAdapterModel(adapter)
            setRecipeAdapterView(adapter)
            getRecipe(intent.getIntExtra("seq", 0 ))
        }



    }
}