package com.thejaljal.jaljal.contract.fragment

import com.thejaljal.jaljal.contract.BasePresenter
import com.thejaljal.jaljal.contract.BaseView
import com.thejaljal.jaljal.contract.adapter.RecipeBookAdapterContract

/**
 * Created by no.1 on 2017-11-15.
 */

interface RecipeBookContract{
    interface View: BaseView{
        fun showDialog(isStyle:Boolean, strs: ArrayList<String>)
    }

    interface Presenter: BasePresenter<View> {

        fun setRecipeBookAdapterModel(model: RecipeBookAdapterContract.Model)
        fun setRecipeBookAdapterView(view: RecipeBookAdapterContract.View)
        fun getRecipeBook()
        fun getMainIngrList()
        fun getProgramList()
        /**
         * HTTP
         */

        fun bookListResponse(data: Any)
        fun mainIngrResponse(data: Any)
        fun programResponse(data: Any)
        fun handleError(error: Throwable)

    }
}