package com.thejaljal.jaljal.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.contract.fragment.RecipeBookContract
import com.thejaljal.jaljal.contract.fragment.RecipeBookPresenter
import com.thejaljal.jaljal.listner.OnItemClickListner
import com.thejaljal.jaljal.view.adapter.RecipeBookAdapter
import com.thejaljal.jaljal.view.dialog.StringDialog
import kotlinx.android.synthetic.main.fragment_recipe_book.view.*
import test.ym.kotilnpj.utils.DebugUtils


/**
 * Created by no.1 on 2017-10-11.
 */

class RecipeBookFragment: CommonFragment<RecipeBookContract.View, RecipeBookContract.Presenter>(), RecipeBookContract.View{

    val TAG = javaClass.simpleName



    override fun onCreatePresenter(): RecipeBookContract.Presenter? = RecipeBookPresenter(context)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.fragment_recipe_book, container, false) as View
        val adapter = RecipeBookAdapter(context)
        v.rv.adapter = adapter
        with(presenter){
            this?.setRecipeBookAdapterModel(adapter)
            this?.setRecipeBookAdapterView(adapter)
            this?.getRecipeBook()
        }

        v.main_ingr_rl.setOnClickListener({ presenter?.getMainIngrList() })
        v.style_rl.setOnClickListener({presenter?.getProgramList()})

        return v
    }


    override fun showDialog(isStyle: Boolean, list: ArrayList<String>) {
        val dialog = StringDialog(context, list)
        dialog.setOnItemClick(object: StringDialog.SelectListner{
                    override fun selectListner(position: Int, str: String) {
                        if(isStyle)view?.style_tv?.text = str
                        else view?.ingr_tv?.text = str
                    }

                })
        dialog.show()
    }

}