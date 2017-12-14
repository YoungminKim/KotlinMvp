package com.thejaljal.jaljal.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.thejaljal.jaljal.AppConst
import com.thejaljal.jaljal.HandlerCode
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.contract.activity.SplashContract
import com.thejaljal.jaljal.contract.activity.SplashPresent
import com.thejaljal.jaljal.view.dialog.DialogView
import test.ym.kotilnpj.manager.PreferencesManager

/**
 * Created by no.1 on 2017-08-30.
 */


class SplashActivity : CommonActivity<SplashContract.View, SplashPresent>(), SplashContract.View {

    private val CLOSE_TIME = 1 * 1000


    override fun onCreatePresenter(): SplashPresent? = SplashPresent()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        with(presenter){
            this?.setContext(this@SplashActivity)
            this?.checkService()
        }
    }

    override fun onNextPage() {
        mHandler.postDelayed( {
            var intent: Intent
            if(PreferencesManager(this).getAccessKey() != null)intent = Intent(this, MainActivity::class.java)
            else intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, CLOSE_TIME.toLong())

    }


    override fun showOneDialog(title: String?, msg: String?, btn: String, msgWhat: Int) {
        DialogView.oneButtonDialogShow(this, title, msg, btn, mHandler, msgWhat)
    }

    override fun showTwoDialog(title: String?, msg: String?, btn1: String, btn2: String, msgWhat1: Int, msgWhat2: Int) {
        DialogView.twoButtonDialogShow(this, title, msg, btn1, btn2, mHandler, msgWhat1, msgWhat2)
    }



    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            var intent: Intent? = null

            when (msg.what) {
                HandlerCode.NEXT_PAGE -> if (PreferencesManager(this@SplashActivity).isLogin()) intent = Intent(this@SplashActivity, MainActivity::class.java)
                else intent = Intent(this@SplashActivity, LoginActivity::class.java)
                HandlerCode.GO_MARKET -> {
                    intent = Intent(Intent.ACTION_VIEW)
                    intent.setData(Uri.parse(AppConst.MARKET_URL + this@SplashActivity.packageName))
                }
                HandlerCode.FINISH -> finish()
            }

            if (intent != null) {
                startActivity(intent)
                finish()
            }

        }
    }

}