package com.thejaljal.jaljal.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.contract.fragment.RecipeBookContract
import com.thejaljal.jaljal.contract.fragment.RecipeBookPresenter
import com.thejaljal.jaljal.listner.EndlessRecyclerOnScrollListener
import com.thejaljal.jaljal.view.adapter.RecipeBookAdapter
import com.thejaljal.jaljal.view.dialog.StringDialog
import kotlinx.android.synthetic.main.fragment_recipe_book.view.*
import test.ym.kotilnpj.utils.DebugUtils


/**
 * Created by no.1 on 2017-10-11.
 */

class RecipeBookFragment: CommonFragment<RecipeBookContract.View, RecipeBookContract.Presenter>(), RecipeBookContract.View{

    val TAG = javaClass.simpleName

    var selectIngr = ""
    var selectStyle = 1

    override fun onCreatePresenter(): RecipeBookContract.Presenter? = RecipeBookPresenter(context)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.fragment_recipe_book, container, false) as View

        v?.run {
            val adapter = RecipeBookAdapter(context)
            rv.adapter = adapter
            presenter?.run {
                setRecipeBookAdapterModel(adapter)
                setRecipeBookAdapterView(adapter)
                getRecipeBook()


                main_ingr_rl.setOnClickListener({ getMainIngrList() })
                style_rl.setOnClickListener({getProgramList()})


                rv.addOnScrollListener(object :EndlessRecyclerOnScrollListener(){
                    override fun onLoadMore(currentPage: Int) {
                        DebugUtils.setLog(TAG, "currentPage : " + currentPage)
                        getRecipeBook(currentPage)
                    }

                })
            }
        }


        return v
    }


    override fun showDialog(isStyle: Boolean, list: ArrayList<String>) {
        val dialog = StringDialog(context, list)
        dialog.setOnItemClick(object: StringDialog.SelectListner{
                    override fun selectListner(position: Int, str: String) {
                        if(isStyle){
                            selectStyle = position +1
                            view?.style_tv?.text = str
                        }else{
                            selectIngr = str
                            view?.ingr_tv?.text = selectIngr
                        }

                        presenter?.getRecipeBook(1, selectIngr , selectStyle)
                    }

                })
        dialog.show()
    }

}