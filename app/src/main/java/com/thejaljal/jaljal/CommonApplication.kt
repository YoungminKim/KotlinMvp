package com.thejaljal.jaljal

import android.app.Activity
import android.app.Application
import com.facebook.FacebookSdk
import com.kakao.auth.*

/**
 * Created by no.1 on 2017-08-31.
 */


class CommonApplication: Application(), IApplicationConfig {
    companion object {
        @Volatile @JvmStatic var currentActivity:Activity? = null
        @Volatile @JvmStatic var instance:CommonApplication? = null

        @JvmStatic val getGlobalApplicationContext: CommonApplication?
            get() {
                if(instance == null)
                    throw IllegalStateException("this application does not inherit com.thejaljal.jaljal.GlobalApplication")
                return instance
            }
    }


    override fun onCreate() {
        super.onCreate()
        instance = this

        /*Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "fonts/noto sans(txt)/notosanskr_regular.otf"))
                .addBold(Typekit.createFromAsset(this, "fonts/noto sans(txt)/notosanskr_bold.otf"))
                .addItalic(Typekit.createFromAsset(this, "fonts/noto sans(txt)/notosanskr_medium.otf"))*/
        KakaoSDK.init(KakaoSDKAdapter())
        FacebookSdk.sdkInitialize(applicationContext)
    }

    override fun onTerminate() {
        super.onTerminate()
        instance = null
    }


    class KakaoSDKAdapter : KakaoAdapter(){
        override fun getSessionConfig(): ISessionConfig {
            return object : ISessionConfig{
                override fun isSaveFormData(): Boolean = true
                override fun getAuthTypes(): Array<AuthType> = arrayOf<AuthType>(AuthType.KAKAO_STORY)

                override fun isSecureMode(): Boolean = false

                override fun getApprovalType(): ApprovalType = ApprovalType.INDIVIDUAL

                override fun isUsingWebviewTimer(): Boolean = true

            }
        }

        override fun getApplicationConfig(): IApplicationConfig? = getGlobalApplicationContext

    }

}