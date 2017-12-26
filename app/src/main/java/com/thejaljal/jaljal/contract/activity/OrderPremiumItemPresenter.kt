package com.thejaljal.jaljal.contract.activity

import android.content.Context
import android.content.Intent
import com.thejaljal.jaljal.common.DateStrReplace
import com.thejaljal.jaljal.contract.AbstractPresenter
import com.thejaljal.jaljal.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import test.ym.kotilnpj.http.HttpsManager
import test.ym.kotilnpj.manager.PreferencesManager
import test.ym.kotilnpj.utils.DebugUtils

/**
 * Created by no.1 on 2017-12-20.
 */

class OrderPremiumItemPresenter(val ctx: Context): AbstractPresenter<OrderPremiumItemContract.View>(), OrderPremiumItemContract.Presenter{


    val TAG = javaClass.simpleName

    val mCompositeDisposable: CompositeDisposable by lazy{
        CompositeDisposable()
    }

    val accessKey = PreferencesManager(ctx).getAccessKey()
    lateinit var mList: ArrayList<PremiumItem.Item>
    lateinit var calList: ArrayList<RecipeCalendar.CalendarData>
    var position = 0

    override fun setSelectPosition(position: Int) {
        this.position = position
    }

    override fun setExtras(intent: Intent) {
        mList = intent.getParcelableArrayListExtra("checkList")
        position = intent.getIntExtra("position", 0)
        calList = intent.getParcelableArrayListExtra("datas")

        setSelectDate(position)
    }

    override fun getDataStringArr(): ArrayList<String> {
        val list = arrayListOf<String>()
        for (it in calList){
            it.run {
                var dateStr = deliveryDate
                if(dateStr == null || dateStr.isEmpty()) dateStr = predictDate

                list.add(DateStrReplace.setDateKoreanYMDW(dateStr)!!)
            }

        }

        return list
    }

    override fun setSelectDate(position: Int) {
        var selectDateStr: String? = null

        selectDateStr = calList[position].deliveryDate
        if(selectDateStr == null || selectDateStr.isEmpty()) selectDateStr = calList[position].predictDate
        view?.selectDate(DateStrReplace.setDateKoreanYMDW(selectDateStr))

    }

    override fun getUserInfo() {

        mCompositeDisposable?.add(HttpsManager.service.getUserInfo(accessKey)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::userInfoHandler, this::handleError))

    }

    override fun getDate() {
        val params = hashMapOf<String, Any>()
        params.put("accessKey", accessKey)

        mCompositeDisposable?.add(HttpsManager.service.getPremiumDates(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::dateHandler, this::handleError))

    }

    override fun orderItems() {
        val params = hashMapOf<String, Any>()
        params.put("accessKey", accessKey)
        for(i in 0..mList.size-1){
            params.put("addtOrderList[$i].addtSeq", mList[i].addtseq)
        }
        calList[position]?.run {
            params.put("weekStartdate", weekStartdate)
            params.put("ordrStatus", ordrStatus!!)
        }

        mCompositeDisposable?.add(HttpsManager.service.orderPremiumItems(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::orderHandler, this::handleError))

    }


    override fun orderHandler(response: OrderPremiumItem) {
        response?.run {
            if(result!!){
                view?.hideView()
            }

            view?.setToast(message!!)
        }
    }
    override fun userInfoHandler(response: UserInfo) {
        response?.run {
            if(result!!){
                var addtPrice = 0
                mList?.filter { it.addtPrice > 0 }.map { addtPrice += it.addtPrice }
                view?.run {
                    setPlanPrice(data?.prgrName!!, data?.prgrPrice!!)
                    setTotalPrice(addtPrice)
                    showView()
                }
            }

        }
    }

    override fun dateHandler(response: PremiumDate) {
        response?.run {
            //TODO
        }
    }

    override fun handleError(error: Throwable){
        view?.setToast(error.localizedMessage)
    }

}