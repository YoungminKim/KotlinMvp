package com.thejaljal.jaljal.contract.activity

import com.thejaljal.jaljal.contract.BasePresenter
import com.thejaljal.jaljal.contract.BaseView
import com.thejaljal.jaljal.contract.adapter.RecipeAdapterContract
import com.thejaljal.jaljal.model.Recipe

/**
 * Created by no.1 on 2017-10-17.
 */

interface RecipeContract{
    interface View: BaseView{

    }

    interface Presenter: BasePresenter<View>{
        fun getRecipe(seq: Int)

        fun setRecipeAdapterView(view: RecipeAdapterContract.View)
        fun setRecipeAdapterModel(model: RecipeAdapterContract.Model)
        fun handlerResponse(data: Recipe)
        fun handleError(error: Throwable)
    }
}