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

    override fun getFaqList() {
        val params = hashMapOf<String, Any>()
        params.put("accessKey", PreferencesManager(ctx).getAccessKey())
        params.put("nowPage", 0)
        params.put("pagingUnit", AppConst.PAGING_UNIT)
        if(mCompositeDisposable == null) mCompositeDisposable = CompositeDisposable()
        mCompositeDisposable?.add(HttpsManager.service.faqList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handlerResponse, this::handleError))

    }

    override fun setFaqAdapterView(view: FaqAdapterContract.View) {
        adapterView = view
    }

    override fun setFaqAdapterModel(model: FaqAdapterContract.Model) {
        adapterModel = model
    }

    override fun handlerResponse(data: Any) {
        val result = data as Faq
        DebugUtils.setLog(TAG, "data : " + result)
        result.let {
            if(it.result!!){
                adapterModel.addData(it.data?.faqList!!)
                adapterView.notifyAdapter()
            }else{
                view?.setToast(it.message!!)
            }
        }
    }

    override fun handleError(error: Throwable) {
        DebugUtils.setLog(TAG, error.localizedMessage)
    }

}