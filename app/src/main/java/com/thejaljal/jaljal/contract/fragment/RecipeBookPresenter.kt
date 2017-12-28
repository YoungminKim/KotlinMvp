package com.thejaljal.jaljal.contract.fragment

import android.content.Context
import com.thejaljal.jaljal.AppConst
import com.thejaljal.jaljal.contract.AbstractPresenter
import com.thejaljal.jaljal.contract.adapter.RecipeBookAdapterContract
import com.thejaljal.jaljal.model.MainIngr
import com.thejaljal.jaljal.model.Program
import com.thejaljal.jaljal.model.Recipe
import com.thejaljal.jaljal.model.RecipeBook
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import test.ym.kotilnpj.http.HttpsManager
import test.ym.kotilnpj.manager.PreferencesManager
import test.ym.kotilnpj.utils.DebugUtils
import java.util.HashMap

/**
 * Created by no.1 on 2017-11-15.
 */

class RecipeBookPresenter(val ctx: Context): AbstractPresenter<RecipeBookContract.View>(), RecipeBookContract.Presenter{



    private val TAG = javaClass.simpleName

    lateinit var adapterModel: RecipeBookAdapterContract.Model
    lateinit var adapterView: RecipeBookAdapterContract.View

    var searchIngr = ""
    var searchStyle = 1

    var page = 0
    var lastPage = false


    var mCompositeDisposable: CompositeDisposable? = null

    override fun setRecipeBookAdapterModel(model: RecipeBookAdapterContract.Model) {
        adapterModel = model
    }

    override fun setRecipeBookAdapterView(view: RecipeBookAdapterContract.View) {
        adapterView = view
    }

    override fun getRecipeBook(page: Int, ingr: String, style: Int) {

        searchIngr = ingr
        searchStyle = style

        DebugUtils.setLog(TAG, "ingr : $ingr, style: $style")
        this.page = page
        if(page == 1) lastPage = false

        if(!lastPage){
            val params = HashMap<String, Any>()
            params.put("accessKey", PreferencesManager(ctx).getAccessKey())
            params.put("nowPage", page)
            params.put("pagingUnit", AppConst.PAGING_UNIT)
            params.put("searchValue2", searchIngr)
            params.put("prgrSeq", searchStyle)

            if(mCompositeDisposable == null) mCompositeDisposable = CompositeDisposable()
            mCompositeDisposable?.add(HttpsManager.service.recipeBook(params)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::bookListResponse, this::handleError))
        }

    }

    override fun bookListResponse(response: RecipeBook) {

        response.run {
            if(result!!){
                data?.recipeList?.run {

                    if(size < AppConst.PAGING_UNIT){
                        lastPage = true
                    }

                    if(page > 1 && adapterModel.list.size > 0 && get(size -1).rcipSeq == adapterModel.list[adapterModel.list.size - 1].rcipSeq){
                        lastPage = true
                        return@run
                    }

                    adapterModel.addList(page, data?.recipeList)
                    adapterView.notifyAdapter()
                }

            }

        }
    }


    override fun getMainIngrList() {
        DebugUtils.setLog(TAG, "getMainIngrList called!!!")
        val params = HashMap<String, Any>()
        params.put("accessKey", PreferencesManager(ctx).getAccessKey())
        if(mCompositeDisposable == null) mCompositeDisposable = CompositeDisposable()
        mCompositeDisposable?.add(HttpsManager.service.getMainIngrList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::mainIngrResponse, this::handleError))

    }

    override fun getProgramList() {
        val params = HashMap<String, Any>()
        params.put("accessKey", PreferencesManager(ctx).getAccessKey())
        if(mCompositeDisposable == null) mCompositeDisposable = CompositeDisposable()
        mCompositeDisposable?.add(HttpsManager.service.getProgramList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::programResponse, this::handleError))
    }

    override fun mainIngrResponse(data: Any) {
        val result = data as MainIngr
        result.let {
            if(result.result!!){
                view?.showDialog(false, it.data?.ingrList!!)
            }

        }
    }

    override fun programResponse(data: Any) {
        val result = data as Program
        result.let {
            if(result.result!!){

                val nameStrs = arrayListOf<String>()
                for(item in result.data?.programList!!){
                    nameStrs.add(item.prgrName)
                }

                view?.showDialog(true, nameStrs)
            }

        }
    }

    override fun handleError(error: Throwable) {
        DebugUtils.setLog(TAG, "error : " + error.localizedMessage)
    }

}