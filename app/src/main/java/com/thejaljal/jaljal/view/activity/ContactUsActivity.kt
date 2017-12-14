package com.thejaljal.jaljal.view.activity

import android.os.Bundle
import android.view.View
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.contract.activity.ContactUsContract
import com.thejaljal.jaljal.contract.activity.ContactUsPresenter
import com.thejaljal.jaljal.utils.CheckPatternUtils
import com.thejaljal.jaljal.view.dialog.StringDialog
import kotlinx.android.synthetic.main.activity_contact_us.*
import test.ym.kotilnpj.utils.DebugUtils

/**
 * Created by no.1 on 2017-12-04.
 */

class ContactUsActivity: CommonActivity<ContactUsContract.View, ContactUsPresenter>(), ContactUsContract.View, View.OnClickListener{

    val TAG = javaClass.simpleName

    var inquryStr: String? = null

    override fun onCreatePresenter(): ContactUsPresenter? = ContactUsPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)
        setTitlebarView(sub_ll)
        setTitleTv("1대1 문의하기")
        presenter?.getUserInfo()

    }

    override fun setName(name: String) {
        name_tv.text = name
    }

    override fun writeComplete() {
        finish()
    }


    override fun onClick(v: View?) {
        when(v){
            inquiry_rl -> {
                val inquirys = resources.getStringArray(R.array.type_of_inquiry)

                val dialog = StringDialog(this,inquirys)
                dialog.setOnItemClick(object : StringDialog.SelectListner{
                    override fun selectListner(str: String) {
                            inquiry_tv.text = str
                    }
                })
                dialog.show()
            }
            send_tv -> {
                if(checkInput())presenter?.writeContact()
            }
        }
    }

    private fun checkInput(): Boolean {

        if (inquryStr == null) {
            setToast("문형을 선택해주세요.")
            return false
        }

        if (email_et.text.toString().isEmpty()) {
            email_et.requestFocus()
            setToast("이메일을 입력해주세요.")
            return false
        }

        if (!CheckPatternUtils.checkEmail(email_et.text.toString().trim())) {
            email_et.requestFocus()
            setToast("이메일형식에 맞혀 입력해주세요.")
            return false
        }

        if (phone_et.text.toString().isEmpty()) {
            phone_et.requestFocus()
            setToast("전화번호를 입력해주세요.")
            return false
        }

        if (!CheckPatternUtils.checkPhone(phone_et.text.toString())) {
            phone_et.requestFocus()
            setToast("핸드폰 맞혀 입력해주세요.")
            return false
        }


        if (subject_et.text.toString().isEmpty()) {
            subject_et.requestFocus()
            setToast("제목을 입력해주세요.")
            return false
        }



        if (content_et.text.toString().isEmpty()) {
            content_et.requestFocus()
            setToast("내용을 입력해주세요.")
            return false
        }

        if (!provision_cb.isChecked) {
            setToast("개인정보ㆍ이용에 동의해주세요")
            return false
        }

        return true
    }

}