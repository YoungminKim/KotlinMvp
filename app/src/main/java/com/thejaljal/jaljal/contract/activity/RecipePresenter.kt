package com.thejaljal.jaljal.contract.activity

import android.content.Context
import com.thejaljal.jaljal.contract.AbstractPresenter
import com.thejaljal.jaljal.contract.adapter.RecipeAdapterContract
import com.thejaljal.jaljal.model.Recipe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import test.ym.kotilnpj.http.HttpsManager
import test.ym.kotilnpj.manager.PreferencesManager

/**
 * Created by no.1 on 2017-10-17.
 */

class RecipePresenter(val ctx: Context): AbstractPresenter<RecipeContract.View>(), RecipeContract.Presenter{
    lateinit var adapterModel: RecipeAdapterContract.Model
    lateinit var adapterView: RecipeAdapterContract.View

    var mCompositeDisposable: CompositeDisposable? = null

    override fun getRecipe(seq: Int) {

        val params = hashMapOf<String, Any>()
        params.put("accessKey", PreferencesManager(ctx).getAccessKey())
        params.put("seq", seq)
        if(mCompositeDisposable == null) mCompositeDisposable = CompositeDisposable()
        mCompositeDisposable?.add(HttpsManager.service.recipe(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handlerResponse, this::handleError))


    }

    override fun handlerResponse(result: Recipe) {
        result.let {
            if(result.result!!){
                adapterModel.addData(result.data!!)
                adapterView.notifyAdapter()
            }
        }
    }

    override fun handleError(error: Throwable) {
    }

    override fun setRecipeAdapterView(view: RecipeAdapterContract.View) {
        adapterView = view
    }

    override fun setRecipeAdapterModel(model: RecipeAdapterContract.Model) {
        adapterModel = model
    }

}