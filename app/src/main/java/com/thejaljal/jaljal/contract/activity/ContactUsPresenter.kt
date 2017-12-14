package com.thejaljal.jaljal.contract.activity

import android.content.Context
import com.thejaljal.jaljal.contract.AbstractPresenter
import com.thejaljal.jaljal.model.Common
import com.thejaljal.jaljal.model.UserInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import test.ym.kotilnpj.http.HttpsManager
import test.ym.kotilnpj.manager.PreferencesManager
import test.ym.kotilnpj.utils.DebugUtils

/**
 * Created by no.1 on 2017-12-04.
 */

class ContactUsPresenter(val ctx: Context): AbstractPresenter<ContactUsContract.View>(), ContactUsContract.Presenter{
    val TAG = javaClass.simpleName

    var mCompositeDisposable: CompositeDisposable? = null
    val pm: PreferencesManager by lazy{
        PreferencesManager(ctx)
    }

    override fun getUserInfo() {
        DebugUtils.setLog(TAG ,"getUserInfo called!!!")
        if(mCompositeDisposable == null) mCompositeDisposable = CompositeDisposable()
        mCompositeDisposable?.add(HttpsManager.service.getUserInfo(pm.getAccessKey())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::userInfoResponse, this::handleError))

    }

    override fun writeContact() {
        val params = HashMap<String, Any>()
        params.put("accessKey", pm.getAccessKey())

        if(mCompositeDisposable == null) mCompositeDisposable = CompositeDisposable()
        mCompositeDisposable?.add(HttpsManager.service.writeInquiry(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::writeResponse, this::handleError))
    }

    override fun userInfoResponse(data: Any) {
        DebugUtils.setLog(TAG ,"userInfoResponse called!!!")
        val result = data as UserInfo
        DebugUtils.setLog(TAG ,"result : " + result)
        result.let {
            if(it.result!!){
                view?.setName(it.data!!.deliveryNm)
            }else{
                view?.setToast(it.message!!)
            }
        }
    }

    override fun writeResponse(data: Any) {
        val result = data as Common
        result.let {
            view?.setToast(it.message!!)
            if(it.result!!){
                view?.writeComplete()
            }
        }
    }


    override fun handleError(error: Throwable) {
        DebugUtils.setLog(TAG ,"handleError called!!!")
        view?.setToast(error.localizedMessage)
    }

}