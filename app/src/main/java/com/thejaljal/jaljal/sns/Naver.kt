package com.thejaljal.jaljal.sns

import android.app.Activity
import android.content.Context
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler

/**
 * Created by no.1 on 2017-09-01.
 */


object Naver{


    interface LoginListner{
        fun onSuccess(accessToken: String, refreshToken: String, expiresAt: Long, tokenType: String)
        fun onFailed(errorCode: String, errorDesc: String)
    }


    private val OAUTH_CLIENT_ID = "BoucAQFQ3bsmVTIwuK_9"
    private val OAUTH_CLIENT_SECRET = "yQV2v6ohYw"
    private val OAUTH_CLIENT_NAME = "네이버 아이디 로그인"


    fun login(ctx: Context, listner: LoginListner){
        val mOAuthLoginModule = OAuthLogin.getInstance()
        mOAuthLoginModule.init(ctx
                , OAUTH_CLIENT_ID
                , OAUTH_CLIENT_SECRET
                , OAUTH_CLIENT_NAME)


        mOAuthLoginModule.startOauthLoginActivity(ctx as Activity, object : OAuthLoginHandler() {
            override fun run(success: Boolean) {
                if (success){
                    val accessToken = mOAuthLoginModule.getAccessToken(ctx)
                    val refreshToken = mOAuthLoginModule.getRefreshToken(ctx)
                    val expiresAt = mOAuthLoginModule.getExpiresAt(ctx)
                    val tokenType = mOAuthLoginModule.getTokenType(ctx)
                    listner?.onSuccess(accessToken, refreshToken, expiresAt, tokenType)
                }else{
                    listner?.onFailed(mOAuthLoginModule.getLastErrorCode(ctx).code, mOAuthLoginModule.getLastErrorDesc(ctx))
                }
            }

        })
    }

    fun logout(ctx: Context) = OAuthLogin.getInstance().logout(ctx)








}