package com.thejaljal.jaljal.contract.activity

import android.content.Context
import com.thejaljal.jaljal.AppConst
import com.thejaljal.jaljal.contract.AbstractPresenter
import com.thejaljal.jaljal.contract.adapter.FaqAdapterContract
import com.thejaljal.jaljal.model.Faq
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import test.ym.kotilnpj.http.HttpsManager
import test.ym.kotilnpj.manager.PreferencesManager
import test.ym.kotilnpj.utils.DebugUtils

/**
 * Created by no.1 on 2017-11-28.
 */

class FaqPresenter(val ctx: Context): AbstractPresenter<FaqContract.View>(), FaqContract.Presenter{
    private val TAG = javaClass.simpleName
    lateinit var adapterView: FaqAdapterContract.View
    lateinit var adapterModel: FaqAdapterContract.Model

    var mCompositeDisposable: CompositeDisposable? = null

    var lastPage = false


    override fun getFaqList(page: Int) {
        DebugUtils.setLog(TAG , "page : " + page)

        if(!lastPage){

            val params = hashMapOf<String, Any>()
            params.put("accessKey", PreferencesManager(ctx).getAccessKey())
            params.put("nowPage", page)
            params.put("pagingUnit", AppConst.PAGING_UNIT)
            if(mCompositeDisposable == null) mCompositeDisposable = CompositeDisposable()
            mCompositeDisposable?.add(HttpsManager.service.faqList(params)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handlerResponse, this::handleError))
        }


    }

    override fun setFaqAdapterView(view: FaqAdapterContract.View) {
        adapterView = view
    }

    override fun setFaqAdapterModel(model: FaqAdapterContract.Model) {
        adapterModel = model
    }

    override fun handlerResponse(response: Faq) {
        response.run {
            if(result!!){
                data?.faqList?.run {
                    if(size < AppConst.PAGING_UNIT){
                        lastPage = true
                    }

                    if(adapterModel.list.size > 0 && get(size - 1).boardSeq == adapterModel.list[adapterModel.list.size - 1].boardSeq){
                        lastPage = true
                        return@run
                    }
                    adapterModel.addData(data?.faqList)
                    adapterView.notifyAdapter()
                }

            }else{
                view?.setToast(message!!)
            }
        }
    }

    override fun handleError(error: Throwable) {
        DebugUtils.setLog(TAG, error.localizedMessage)
    }

}