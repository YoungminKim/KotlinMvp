package com.thejaljal.jaljal.contract.activity

import android.content.Context
import android.content.Intent
import com.thejaljal.jaljal.contract.AbstractPresenter
import com.thejaljal.jaljal.contract.adapter.PremiumItemAdapterContract
import com.thejaljal.jaljal.model.Common
import com.thejaljal.jaljal.model.PremiumItem
import com.thejaljal.jaljal.model.RecipeCalendar
import com.thejaljal.jaljal.view.activity.OrderPremiumItemActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import test.ym.kotilnpj.http.HttpsManager
import test.ym.kotilnpj.manager.PreferencesManager

/**
 * Created by no.1 on 2017-12-11.
 */

class PremiumItemPresenter(val ctx: Context): AbstractPresenter<PremiumItemContract.View>(), PremiumItemContract.Presenter{


    lateinit var adapterModel: PremiumItemAdapterContract.Model
    lateinit var adapterView: PremiumItemAdapterContract.View

    lateinit var calList: ArrayList<RecipeCalendar.CalendarData>
    var position = -1
    var isOrder = false

    val mCompositeDisposable: CompositeDisposable? by lazy {
        CompositeDisposable()
    }

    val accessKey by lazy{
        PreferencesManager(ctx).getAccessKey()
    }


    override fun adapterView(view: PremiumItemAdapterContract.View) {
        adapterView = view
    }

    override fun adapterModel(model: PremiumItemAdapterContract.Model) {
        adapterModel = model
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

        mCompositeDisposable?.add(HttpsManager.service.getPremiumItems(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::premiumListResponse, this::handleError))
    }

    override fun cancelPremiumItem() {
        val params = hashMapOf<String, Any>()
        params.put("accessKey", accessKey)
        params.put("ordrSeq", calList[position].ordrSeq)
        params.put("weekStartdate", calList[position].weekStartdate)

        mCompositeDisposable?.add(HttpsManager.service.cancelPremiumItem(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::cancelItemResponse, this::handleError))

    }

    override fun checkModifyStatus() {
        val params = hashMapOf<String, Any>()
        params.put("accessKey",  accessKey)
        params.put("weekStartdate", calList[position].weekStartdate)

        mCompositeDisposable?.add(HttpsManager.service.checkModifyStatus(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::checkStatusResponse, this::handleError))
    }


    override fun checkStatusResponse(response: Common) {
        response?.run {
            if(result!!){
                cancelPremiumItem()
            }else{
                view?.setToast("프리미엄 재료를 취소할 수 없습니다.")
            }
        }
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

    override fun cancelItemResponse(response: Common) {
        response?.run {
            if(result!!){
                view?.orderCancle()
            }

            view?.setToast(message!!)
        }
    }




    override fun checkItemList() {
        adapterModel.getCheckItemList()?.let {
            if (it.size > 0){
                val intent = Intent(ctx, OrderPremiumItemActivity::class.java)
                val list = arrayListOf<RecipeCalendar.CalendarData>()
                calList?.filter { it.addtPrice == 0 }.map { list.add(it) }
                intent.apply {
                    putExtra("datas", list)
                    putExtra("position", position)
                    putExtra("checkList", it)
                }

                view?.goOrderPage(intent)
            }else{
                view?.setToast("선택하신 재료가 없습니다.")
            }

        }


    }

}