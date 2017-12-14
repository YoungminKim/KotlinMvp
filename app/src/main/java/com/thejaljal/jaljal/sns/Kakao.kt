package com.thejaljal.jaljal.sns

import android.app.Activity
import android.content.Intent
import com.kakao.auth.AuthType
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.LogoutResponseCallback
import com.kakao.usermgmt.callback.MeResponseCallback
import com.kakao.usermgmt.response.model.UserProfile
import com.kakao.util.exception.KakaoException

/**
 * Created by no.1 on 2017-09-01.
 */

object Kakao{

    interface LoginListner{
        fun onNotSignedUp()
        fun onSuccess(profile: UserProfile)
        fun onSessionClosed(error: String)
    }



    fun login(act: Activity, listner: LoginListner) {
        val profile = UserProfile.loadFromCache()
        if(profile != null && profile.id > 0){
            listner.onSuccess(profile)
        }else{
            Session.getCurrentSession().addCallback(object: ISessionCallback {
                override fun onSessionOpenFailed(exception: KakaoException?) {
                    if (exception != null) exception.printStackTrace()
                }

                override fun onSessionOpened() {
                    UserManagement.requestMe(object: MeResponseCallback() {
                        override fun onSuccess(result: UserProfile) =  listner.onSuccess(profile)

                        override fun onSessionClosed(errorResult: ErrorResult?) = listner.onSessionClosed(errorResult.toString())

                        override fun onNotSignedUp() = listner.onNotSignedUp()
                    })
                }

            })
            Session.getCurrentSession().open(AuthType.KAKAO_STORY, act)
        }
    }

    fun logout(callback: LogoutResponseCallback) = UserManagement.requestLogout(callback)

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent):Boolean = Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)
}