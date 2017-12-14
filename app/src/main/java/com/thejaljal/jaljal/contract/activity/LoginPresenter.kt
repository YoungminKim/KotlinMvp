package com.thejaljal.jaljal.contract.activity

import android.app.Activity
import android.content.Context
import android.content.Intent

import com.kakao.usermgmt.response.model.UserProfile

import com.thejaljal.jaljal.model.Login
import com.thejaljal.jaljal.contract.AbstractPresenter
import com.thejaljal.jaljal.sns.Facebook
import com.thejaljal.jaljal.sns.Instagram
import com.thejaljal.jaljal.sns.Kakao
import com.thejaljal.jaljal.sns.Naver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import test.ym.kotilnpj.http.HttpsManager
import test.ym.kotilnpj.manager.PreferencesManager
import test.ym.kotilnpj.utils.DebugUtils

/**
 * Created by no.1 on 2017-08-31.
 */


class LoginPresenter(val ctx: Context) : AbstractPresenter<LoginContract.View>(), LoginContract.Presenter {

    private val TAG = javaClass.simpleName

    var facebook: Facebook? = null
    var kakao: Kakao? = null
    var mCompositeDisposable: CompositeDisposable? = null

    override fun login(id: String?, pass: String?, key: String?, type: String) {
        val params = hashMapOf<String, Any>()
        when(type){
            "E" -> {
                    params.put("mberId", id!!)
                    params.put("mberPass", pass!!)
            }
            else -> params.put("accessKey", key!!)
        }
        params.put("accessType", type)
        params.put("osType", "ANDROID")
        if(mCompositeDisposable == null)mCompositeDisposable = CompositeDisposable()
        mCompositeDisposable?.add(HttpsManager.service.login(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handlerResponse, this::handleError))

    }


    override fun handlerResponse(data: Any) {
        val result =  data as Login
        result.let {
            if(result.result!!){
                PreferencesManager(ctx).setAccessKey(it.data!!.accessKey)
                view?.loginSuccess(it)
            }else{
                view?.setToast(it.message!!)
            }
        }

    }

    override fun handleError(error: Throwable) {
        //view?.setToast("error : " + error.localizedMessage)
        DebugUtils.setLog(TAG, "error : " + error.localizedMessage)
    }


    override fun naverLogin(){
        Naver.login(ctx, object: Naver.LoginListner {
            override fun onSuccess(accessToken: String, refreshToken: String, expiresAt: Long, tokenType: String) {
                login(null, null, accessToken, "N")
            }

            override fun onFailed(errorCode: String, errorDesc: String) {
                view?.setToast("로그인 실패")
            }
        })
    }

    override fun facebookLogin() {
        Facebook.login(ctx, object: Facebook.LoginListner{
            override fun onCancel() {
                DebugUtils.setLog(TAG, "onCancel called!!!")
            }

            override fun onSuccess(id: String, name: String, picture: String) {
                login(null, null, id, "F")
            }

            override fun onFailed(error: String) {
                view?.setToast("로그인 실패")
            }

        })
    }

    override fun kakaoLogin() {
        Kakao.login(ctx as Activity, object: Kakao.LoginListner{
            override fun onNotSignedUp() {
                view?.setToast("로그인 실패")
            }

            override fun onSuccess(profile: UserProfile) {
                login(null, null, profile.id.toString(), "K")
            }

            override fun onSessionClosed(error: String) {
               DebugUtils.setLog(TAG, "onSessionClosed called!!!")
            }

        })

    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {

        if(requestCode == Instagram.INSTATGRAM && resultCode == Activity.RESULT_OK){
            val token = data.getStringExtra("token")
            login(null, null, token, "I")
            return
        }
        if (kakao != null && kakao?.onActivityResult(requestCode, resultCode, data)!!) return
        if(facebook != null)facebook?.onActivityResult(requestCode, resultCode, data)
    }
}