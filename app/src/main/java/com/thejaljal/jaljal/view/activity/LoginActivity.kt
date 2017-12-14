package com.thejaljal.jaljal.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.model.Login
import com.thejaljal.jaljal.contract.activity.LoginContract
import com.thejaljal.jaljal.contract.activity.LoginPresenter
import com.thejaljal.jaljal.sns.Instagram
import com.thejaljal.jaljal.utils.CheckPatternUtils

import kotlinx.android.synthetic.main.activity_login.*


/**
 * Created by no.1 on 2017-08-30.
 */

class LoginActivity : CommonActivity<LoginContract.View, LoginPresenter>(), LoginContract.View, View.OnClickListener{

    override fun onCreatePresenter(): LoginPresenter? = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    override fun onClick(v: View) {
        when(v){
            login_bt -> if(checkInput())presenter?.login(email_et.text.toString(), pass_et.text.toString(), null, "E")
            sns_bt1 -> presenter?.naverLogin()
            sns_bt2 -> presenter?.facebookLogin()
            sns_bt3 -> presenter?.kakaoLogin()
            sns_bt4 -> startActivityForResult(Intent(this, Instagram::class.java), Instagram.INSTATGRAM)
            email_tv ->{
                email_subject_tv.visibility = View.VISIBLE
                email_tv.visibility = View.GONE
                email_ll.visibility = View.VISIBLE
                email_et.requestFocus()
                email_tv.animation = AnimationUtils.loadAnimation(this, R.anim.slide_up_out)
                email_ll.animation = AnimationUtils.loadAnimation(this, R.anim.slide_up_in)
            }
            pass_tv->{
                pass_subject_tv.visibility = View.VISIBLE
                pass_tv.visibility = View.GONE
                pass_ll.visibility = View.VISIBLE
                pass_et.requestFocus()
                pass_tv.animation = AnimationUtils.loadAnimation(this, R.anim.slide_up_out)
                pass_ll.animation = AnimationUtils.loadAnimation(this, R.anim.slide_up_in)
            }
        }
    }

    override fun loginSuccess(user: Login) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter?.onActivityResult(requestCode, resultCode, data!!)
    }

    fun checkInput(): Boolean{
        if(email_et.text.isEmpty()){
            setToast("이메일을 입력해주세요.")
            email_et.requestFocus()
            return false
        }else if(!CheckPatternUtils.checkEmail(email_et.text.toString())){
            setToast("이메일형식에 맞혀 입력해주세요.")
            email_et.requestFocus()
            return false
        }else if(pass_et.text.isEmpty()){
            setToast("비밀번호를 입력해주세요.")
            pass_et.requestFocus()
            return false
        }

        return true
    }

}