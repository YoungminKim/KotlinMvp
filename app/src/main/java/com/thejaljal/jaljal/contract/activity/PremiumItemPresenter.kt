package com.thejaljal.jaljal.contract.activity

import android.content.Context
import android.content.Intent
import com.thejaljal.jaljal.contract.AbstractPresenter
import com.thejaljal.jaljal.contract.adapter.PremiumItemAdapterContract
import com.thejaljal.jaljal.model.Common
import com.thejaljal.jaljal.model.PremiumItem
import com.thejaljal.jaljal.model.RecipeCalendar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import test.ym.kotilnpj.http.HttpsManager
import test.ym.kotilnpj.manager.PreferencesManager

/**
 * Created by no.1 on 2017-12-11.
 */

class PremiumItemPresenter(val ctx: Context): AbstractPresenter<PremiumItemContract.View>(), PremiumItemContract.Presenter{


    override lateinit var adapterView: PremiumItemAdapterContract.View
    override lateinit var adapterModel: PremiumItemAdapterContract.Model

    lateinit var calList: ArrayList<RecipeCalendar.CalendarData>
    var position = -1
    var isOrder = false

    var mCompositeDisposable: CompositeDisposable? = null

    val accessKey by lazy{
        PreferencesManager(ctx).getAccessKey()
    }

    override fun setExtra(intent: Intent) {
        calList = intent.getParcelableArrayListExtra<RecipeCalendar.CalendarData>("datas")
        position = intent.getIntExtra("position", -1)

        isOrder = calList[position].addtPrice > 0

        view?.setIsOrderView(isOrder)
    }

    override fun getPremiumList() {
        val params = hashMapOf<String, Any>()
        params.put("accessKey", accessKey)
        params.put("ordrSeq", calList[position].ordrSeq)

        if (mCompositeDisposable == null) mCompositeDisposable = CompositeDisposable()

        mCompositeDisposable?.add(HttpsManager.service.getPremiumItems(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::premiumListResponse, this::handleError))
    }

    override fun cancelPremiumItem() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkModifyStatus() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun premiumListResponse(response: PremiumItem) {
        response?.run {
            if(result!!){

                var list = arrayListOf<PremiumItem.Item>()
                if(isOrder) data?.itemList.filter { it.ordrSeq != 0 }.map { list.add(it) }
                else list = data?.itemList

                adapterModel.addData(list)
                adapterView.notifyAdapter()
            }
        }
    }

    override fun handleError(error: Throwable) {
        view?.setToast(error.localizedMessage)
    }

    override fun cancelItemResponse(data: Common) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}