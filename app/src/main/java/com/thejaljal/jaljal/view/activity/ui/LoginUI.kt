package com.thejaljal.jaljal.view.activity.ui

import android.graphics.Color
import android.graphics.Rect
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.utils.CheckPatternUtils
import com.thejaljal.jaljal.view.activity.LoginActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by no.1 on 2018-02-13.
 */


class LoginUI(val mListener: OnClickListener): AnkoComponent<LoginActivity>{

    interface OnClickListener{
        fun emailLogin(email: String, pass: String)
        fun kakaoLogin()
        fun naverLogin()
        fun instaLogin()
        fun facebookLogin()
    }

    lateinit var logoIv: ImageView
    lateinit var keybordMarginV: View
    lateinit var emailEt: EditText
    lateinit var passEt: EditText

    override fun createView(ui: AnkoContext<LoginActivity>) = with(ui){


        verticalLayout {
            backgroundColor = Color.parseColor("#ffffff")
            horizontalPadding = dip(32)

            gravity = Gravity.CENTER_HORIZONTAL

            viewTreeObserver.addOnGlobalLayoutListener {
                val r = Rect()
                getWindowVisibleDisplayFrame(r)
                val heightOff = rootView.height - (r.bottom - r.top)
                if(heightOff > 100) showKeybord()
                else hideKeybord()

            }

            logoIv  = imageView (R.drawable.img_logo){

            }.lparams(width = wrapContent, height = wrapContent){
                topMargin = dip(64)
            }

            view().lparams(width = matchParent, height = 0, weight = 2f)

            val emailTitleTv = textView("이메일"){
                textColor = R.color.app_gray3
                textSize = 16f
                visibility = View.INVISIBLE
            }

            relativeLayout {

                val emaiLl = verticalLayout {
                    visibility = View.GONE

                    emailEt = editText{
                        hint = "email@email.com"
                        inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                        textColor = R.color.app_gray3
                        textSize = 16f
                        background = null
                        horizontalPadding = 0


                    }.lparams(width = matchParent, height = wrapContent)

                    view{ backgroundColor = R.color.app_gray3 }.lparams(width = matchParent, height = dip(1))

                }

                textView("이메일"){
                    textSize = 16f
                    textColor = R.color.app_gray3

                    onClick {
                        emailTitleTv.visibility = View.VISIBLE
                        visibility = View.GONE
                        emaiLl.visibility = View.VISIBLE
                        emailEt?.requestFocus()
                        animation = AnimationUtils.loadAnimation(ctx, R.anim.slide_up_out)
                        emaiLl.animation = AnimationUtils.loadAnimation(ctx, R.anim.slide_up_in)
                    }
                }.lparams(width = wrapContent, height = wrapContent){
                    centerVertically()
                }


            }.lparams(width = matchParent, height = dip(49)){
                bottomMargin = dip(16)
            }


            val passTitleTv = textView("비밀번호"){
                textColor = R.color.app_gray3
                textSize = 16f
                visibility = View.INVISIBLE
            }

            relativeLayout {

                val passLl = verticalLayout {
                    visibility = View.GONE

                    passEt = editText{
                        hint = "··············"
                        inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                        textColor = R.color.app_gray3
                        textSize = 16f
                        background = null
                        horizontalPadding = 0


                    }.lparams(width = matchParent, height = wrapContent)

                    view{ backgroundColor = R.color.app_gray3 }.lparams(width = matchParent, height = dip(1))

                }

                textView("비밀번호"){
                    textSize = 16f
                    textColor = R.color.app_gray3


                    onClick {
                        passTitleTv.visibility = View.VISIBLE
                        visibility = View.GONE
                        passLl.visibility = View.VISIBLE
                        passEt?.requestFocus()
                        animation = AnimationUtils.loadAnimation(ctx, R.anim.slide_up_out)
                        passLl.animation = AnimationUtils.loadAnimation(ctx, R.anim.slide_up_in)
                    }
                }.lparams(width = wrapContent, height = wrapContent){
                    centerVertically()
                }


            }.lparams(width = matchParent, height = dip(49)){
                bottomMargin = dip(16)
            }


            button(R.string.login){
                backgroundDrawable = ContextCompat.getDrawable(ctx, R.drawable.login_box)
                typeface = ResourcesCompat.getFont(ctx, R.font.notosanskr_medium)
                gravity = Gravity.CENTER
                textSize = 18f
                textColor = Color.WHITE
                onClick {
                    val result = checkInput()
                    if(result != null) toast(result)
                    else mListener.emailLogin(email = emailEt?.text.toString(), pass = passEt?.text.toString())

                }

            }.lparams(width = matchParent, height = dip(48)){
                topMargin = dip(32)
            }

            keybordMarginV = view().lparams(width = matchParent, height = 0, weight = 1f)

            textView("SNS 계정으로 로그인"){
                textColor = R.color.app_gray3
                textSize = 16f
            }.lparams(width = matchParent, height = wrapContent){
                verticalMargin = dip(40)
            }


            linearLayout{
                orientation = LinearLayout.HORIZONTAL

                button{
                    backgroundDrawable = ContextCompat.getDrawable(ctx, R.drawable.btn_sns_naver)
                    onClick {
                        mListener.naverLogin()
                    }
                }.lparams(width = wrapContent, height = matchParent)

                view().lparams(width = 0, height = 1, weight = 1f)


                button{
                    backgroundDrawable = ContextCompat.getDrawable(ctx, R.drawable.btn_sns_fb)
                    onClick {
                        mListener.facebookLogin()
                    }
                }.lparams(width = wrapContent, height = matchParent)

                view().lparams(width = 0, height = 1, weight = 1f)


                button{
                    backgroundDrawable = ContextCompat.getDrawable(ctx, R.drawable.btn_sns_kakao)
                    onClick {
                        mListener.kakaoLogin()
                    }
                }.lparams(width = wrapContent, height = matchParent)

                view().lparams(width = 0, height = 1, weight = 1f)


                button{
                    backgroundDrawable = ContextCompat.getDrawable(ctx, R.drawable.btn_sns_insta)
                    onClick {
                        mListener.instaLogin()
                    }
                }.lparams(width = wrapContent, height = matchParent)



            }.lparams(width = matchParent, height = dip(32)){

            }

            relativeLayout {
                textView(R.string.under_find_pass) {
                    textColor = R.drawable.pressed_signup
                    textSize = 14f

                }.lparams{
                    alignParentLeft()
                }


                textView(R.string.under_signup) {
                    textColor = R.drawable.pressed_signup
                    textSize = 14f

                }.lparams{
                    alignParentRight()
                }

            }.lparams(width = matchParent, height = wrapContent){
                topMargin = dip(40)
                bottomMargin = dip(32)

            }

        }
    }

    private fun showKeybord(){
        if(logoIv.visibility == View.VISIBLE){
            logoIv.visibility = View.GONE
            keybordMarginV.visibility = View.VISIBLE
        }
    }

    private fun hideKeybord(){

        if(logoIv.visibility == View.GONE){
            logoIv.visibility = View.VISIBLE
            keybordMarginV.visibility = View.GONE
        }
    }

    private fun checkInput(): String?{

        var result: String? = null


        if(emailEt?.text!!.isEmpty()){
            result = "이메일을 입력해주세요."
            emailEt?.requestFocus()
        }else if(!CheckPatternUtils.checkEmail(emailEt?.text.toString())){
            result = "이메일형식에 맞혀 입력해주세요."
            emailEt?.requestFocus()
        }else if(passEt?.text!!.isEmpty()){
            result = "비밀번호를 입력해주세요."
            passEt?.requestFocus()
        }

        return result
    }

}