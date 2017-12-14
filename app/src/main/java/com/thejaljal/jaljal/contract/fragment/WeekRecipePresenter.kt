package com.thejaljal.jaljal.contract.activity


import android.content.Context
import com.thejaljal.jaljal.AppConst

import com.thejaljal.jaljal.contract.AbstractPresenter
import com.thejaljal.jaljal.contract.adapter.WeekRecipeAdapterContract
import com.thejaljal.jaljal.model.WeekRecipe
import com.thejaljal.jaljal.view.adapter.WeekRecipeAdapter
import io.reactivex.android.schedulers.AndroidSchedulers

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import test.ym.kotilnpj.http.HttpsManager
import test.ym.kotilnpj.manager.PreferencesManager
import test.ym.kotilnpj.utils.DebugUtils

/**
 * Created by no.1 on 2017-08-31.
 */


class WeekRecipePresenter(val ctx: Context) : AbstractPresenter<WeekRecipeContract.View>(), WeekRecipeContract.Presenter {
    private val TAG = javaClass.simpleName

    var mCompositeDisposable: CompositeDisposable? = null
    var nowPage = 1

    val pm = PreferencesManager(ctx)

    lateinit var adapterModel: WeekRecipeAdapterContract.Model
    lateinit var adapterView: WeekRecipeAdapterContract.View

    override fun getWeekRecipe() {

        val params = hashMapOf<String, Any>()
        params.put("accessKey", pm.getAccessKey())
        params.put("nowPage", nowPage)
        params.put("pagingUnit", AppConst.PAGING_UNIT)

        if(mCompositeDisposable == null)mCompositeDisposable = CompositeDisposable()
        mCompositeDisposable?.add(HttpsManager.service.weekRecipe(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handlerResponse, this::handleError))
    }

    override fun handlerResponse(data: Any) {
        val result = data as WeekRecipe
        if(result.result!!){
            adapterModel.addList(result.data?.recipeList!!)
            adapterView.notifyAdapter()
        }
    }

    override fun handleError(error: Throwable) {
        //view?.setToast(error.localizedMessage)
        DebugUtils.setLog(TAG, "error : " + error.localizedMessage)
    }


    override fun setWeekRecipeAdapterModel(model: WeekRecipeAdapterContract.Model) {
        adapterModel = model
    }

    override fun setWeekRecipeAdapterView(view: WeekRecipeAdapterContract.View) {
        adapterView = view
    }

}