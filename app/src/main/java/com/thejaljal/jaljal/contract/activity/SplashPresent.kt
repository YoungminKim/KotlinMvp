package com.thejaljal.jaljal.contract.activity

import android.content.Context
import com.thejaljal.jaljal.HandlerCode
import com.thejaljal.jaljal.contract.AbstractPresenter
import com.thejaljal.jaljal.model.VersionService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import test.ym.kotilnpj.http.HttpsManager
import test.ym.kotilnpj.http.UrlInfo
import test.ym.kotilnpj.manager.AppManager
import test.ym.kotilnpj.manager.PreferencesManager
import test.ym.kotilnpj.utils.DebugUtils

/**
 * Created by no.1 on 2017-09-04.
 */

class SplashPresent: AbstractPresenter<SplashContract.View>(), SplashContract.Presenter {


    private val TAG = javaClass.simpleName

    private var mCompositeDisposable:CompositeDisposable? = null
    private lateinit var mContext: Context


    override fun setContext(ctx: Context) {
        mContext = ctx
    }

    override fun checkService() {
        UrlInfo.HEADER = UrlInfo.URL_HEADER + UrlInfo.DOMAIN
        val params = hashMapOf<String, Any>()
        params.put("osType", "ANDROID")
        params.put("appVersion", AppManager.getAppVerName(mContext)!!)

        if(mCompositeDisposable == null)mCompositeDisposable = CompositeDisposable()
        mCompositeDisposable?.add(HttpsManager.service.checkService(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handlerResponse, this::handleError))
    }


    override fun handlerResponse(data: Any) {
        val result = data as VersionService
        result.let {
            if(result.result!!){
                result.data.let {
                    val serviceStatus: VersionService.ServiceStatus? = result.data!!.ServiceStatus
                    val appStatus: VersionService.AppStatus? = result.data!!.appStatus
                    if(serviceStatus?.serviceYn.equals("Y")){

                        val lastVersionStr = appStatus?.lastVersion!!.replace(".", "")
                        DebugUtils.setLog(TAG, "" + lastVersionStr)
                        val lastVersion = Integer.parseInt(lastVersionStr)

                        UrlInfo.API_HEADER = appStatus?.apiUrl.toString()
                        HttpsManager.setService()

                        if(AppManager.getAppVerCode(mContext)!! < lastVersion){
                            if(appStatus.updateYn.equals("Y")) view?.showOneDialog(null, "최신 버전이 있습니다.\\n업데이트를 하셔야 이용가능 합니다.", "확인", HandlerCode.GO_MARKET)
                            else{
                                val pm = PreferencesManager(mContext)
                                if(pm.getLastVersion() < lastVersion){
                                    pm.setLastVersion(lastVersion)
                                    view?.showTwoDialog(null, "최신 버전이 있습니다.\n업데이트 하시겠습니까?", "확인", "취소", HandlerCode.GO_MARKET, HandlerCode.NEXT_PAGE)
                                }else{
                                    view?.onNextPage()
                                }
                            }
                        }else{
                            view?.onNextPage()
                        }

                    }else{
                        view?.showOneDialog(null, serviceStatus?.statusMsg, "확인", HandlerCode.FINISH)
                    }
                }
            }else{
                view?.setToast(result.message!!)
            }
        }
    }

    override fun handleError(error: Throwable) {
        view?.setToast(error.localizedMessage)
    }

}