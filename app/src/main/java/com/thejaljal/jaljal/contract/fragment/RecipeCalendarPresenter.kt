package com.thejaljal.jaljal.contract.fragment

import android.content.Context
import android.content.Intent
import android.view.View
import com.thejaljal.jaljal.AppConst
import com.thejaljal.jaljal.common.DateStrReplace
import com.thejaljal.jaljal.contract.AbstractPresenter
import com.thejaljal.jaljal.contract.adapter.RecipeCaladarAdapterContract
import com.thejaljal.jaljal.listner.RecipeCalendarListner
import com.thejaljal.jaljal.model.Common
import com.thejaljal.jaljal.model.RecipeCalendar
import com.thejaljal.jaljal.view.activity.PremiumItemActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import test.ym.kotilnpj.http.HttpsManager
import test.ym.kotilnpj.manager.PreferencesManager
import test.ym.kotilnpj.utils.DebugUtils
import java.util.HashMap

/**
 * Created by no.1 on 2017-10-11.
 */
class RecipeCalendarPresenter(val ctx: Context): AbstractPresenter<RecipeCalendarContract.View>(), RecipeCalendarContract.Presenter, RecipeCalendarListner{



    val TAG = javaClass.simpleName

    lateinit var adapterModel: RecipeCaladarAdapterContract.Model
    var adapterView: RecipeCaladarAdapterContract.View? = null
    set(value) {
        field = value
        field?.onDeliveryClick = { b :Boolean, i: Int -> onDeliveryClick(b, i) }
        field?.onCheckModifyStatus = { onCheckModifyStatus(it) }
        field?.showToast = { showToast(it) }
    }

    var mCompositeDisposable: CompositeDisposable? = null

    var nowPage = 1

    val pm = PreferencesManager(ctx)

    var clickPosition: Int = -1
    var clickItem: RecipeCalendar.CalendarData? = null
    var dilveryCheck = false

    override fun setUpdateIntent(intent: Intent): Intent = intent

    override fun getRecipeCalandar() {
        val params = HashMap<String, Any>()
        params.put("accessKey", pm.getAccessKey())
        params.put("nowPage", nowPage)
        params.put("pagingUnit", AppConst.PAGING_UNIT)
        if(mCompositeDisposable == null)mCompositeDisposable = CompositeDisposable()
        mCompositeDisposable?.add(HttpsManager.service.recipeCalendar(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handlerResponse, this::handleError))

    }

    override fun handlerResponse(data: Any) {
        val result =  data as RecipeCalendar
        result.let {
            if(result.result!!){
                adapterModel.addList(result.data.calendarList)
                adapterView?.notifyAdapter()
                orderStatus()

            }
        }
    }

    override fun handleError(error: Throwable) {
        DebugUtils.setLog(TAG, "handleError : " + error.stackTrace)
        view?.setToast(error.message!!)
    }


    override fun orderStatus() {

        var visible = View.GONE
        var nextDelivery = ""

        for(item in adapterModel.list){
            if(item.ordrStatus.equals("O")){

                visible = View.VISIBLE
                var dateStr = item.deliveryDate
                if(dateStr == null || dateStr.isEmpty()) dateStr = item.predictDate
                nextDelivery = DateStrReplace.setDateKorean(dateStr!!)

                break
            }
        }


        view?.setDeliveryLayout(nextDelivery, visible)
    }

    override fun setRecipeCalandarAdapterModel(model: RecipeCaladarAdapterContract.Model) {
        adapterModel = model
    }

    override fun setRecipeCalandarAdapterView(view: RecipeCaladarAdapterContract.View) {
        adapterView = view
    }



    override fun onDeliveryClick(status: Boolean, position: Int) {
        clickPosition = position
        adapterModel.getItem(position).let {

            dilveryCheck = status
            updateDeliveryStatus(it)
        }
    }

    override fun onCheckModifyStatus(position: Int) {
        clickPosition = position
        adapterModel.getItem(position).let {
            checkModifyStatus(it)
        }
    }


    override fun updateDeliveryStatus(item: RecipeCalendar.CalendarData) {

        clickItem = item

        val params = hashMapOf<String, Any>()
        params.put("accessKey",  pm.getAccessKey())
        params.put("weekStartdate", item.weekStartdate)

        var ordrStatus = "O"
        if(!dilveryCheck) ordrStatus = "P"

        params.put("ordrStatus", ordrStatus)

        if(mCompositeDisposable == null)mCompositeDisposable = CompositeDisposable()
        mCompositeDisposable?.add(HttpsManager.service.setDeliveryStatus(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::deliveryStatusResponse, this::handleError))
    }

    override fun deliveryStatusResponse(data: Common) {
        data?.let {
            if(it.result!!){
                clickItem.let {
                    if(dilveryCheck){
                        it?.ordrStatus = "O"
                    }else{
                        it?.ordrStatus = "P"
                    }
                    it?.addtPrice = 0
                    adapterView?.setDeliveryStatus(clickPosition, it)
                }

            }
            view?.setToast(it.message!!)
            adapterView?.notifyAdapter()
            orderStatus()
        }
    }

    override fun checkModifyStatus(item: RecipeCalendar.CalendarData) {

        val params = hashMapOf<String, Any>()
        params.put("accessKey",  pm.getAccessKey())
        params.put("weekStartdate", item.weekStartdate)

        if(mCompositeDisposable == null)mCompositeDisposable = CompositeDisposable()
        mCompositeDisposable?.add(HttpsManager.service.checkModifyStatus(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::checkStatusResponse, this::handleError))
    }


    override fun checkStatusResponse(data: Common) {

        data.let {
            if(it.result!!){


                var list = arrayListOf<RecipeCalendar.CalendarData>()

                adapterModel.list.filter { it.ordrStatus.equals("O") }.map { list.add(it) }
                var putPosition = 0
                for(i in 0.. list.size -1){
                    if(adapterModel.list[clickPosition].ordrSeq == list[i].ordrSeq){
                        putPosition = i
                        break;
                    }
                }

                val intent  = Intent(ctx, PremiumItemActivity::class.java)
                intent.apply {
                    putExtra("datas", list)
                    putExtra("position", putPosition)
                }


                view?.goPremiumIngr(intent)

            }else{
                view?.setToast("프리미엄 재료를 추가할수 없습니다.")
            }
        }

    }


    override fun showToast(str: String) {
        view?.setToast(str)
    }
}