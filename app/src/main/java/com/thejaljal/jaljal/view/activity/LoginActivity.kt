package com.thejaljal.jaljal.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.thejaljal.jaljal.model.Login
import com.thejaljal.jaljal.contract.activity.LoginContract
import com.thejaljal.jaljal.contract.activity.LoginPresenter
import com.thejaljal.jaljal.sns.Instagram
import com.thejaljal.jaljal.view.activity.ui.LoginUI

import org.jetbrains.anko.*



/**
 * Created by no.1 on 2017-08-30.
 */

class LoginActivity : CommonActivity<LoginContract.View, LoginPresenter>(), LoginContract.View{



    override fun onCreatePresenter(): LoginPresenter? = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ui = LoginUI(object : LoginUI.OnClickListener {
            override fun emailLogin(email: String, pass: String) {
                presenter?.login(email, pass, null, "E")
            }

            override fun kakaoLogin() {
                presenter?.kakaoLogin()
            }

            override fun naverLogin() {
                presenter?.naverLogin()
            }

            override fun instaLogin() {
                startActivityForResult(Intent(this@LoginActivity, Instagram::class.java), Instagram.INSTATGRAM)
            }

            override fun facebookLogin() {
                presenter?.facebookLogin()
            }
        })

        ui.setContentView(this)
    }


    override fun loginSuccess(user: Login) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter?.onActivityResult(requestCode, resultCode, data!!)
    }

}