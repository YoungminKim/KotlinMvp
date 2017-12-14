package com.thejaljal.jaljal.view.activity

import android.arch.lifecycle.LifecycleActivity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.webkit.CookieManager
import android.webkit.CookieSyncManager
import android.widget.TextView
import android.widget.Toast
import com.kakao.usermgmt.callback.LogoutResponseCallback
import com.thejaljal.jaljal.CommonApplication
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.contract.BasePresenter
import com.thejaljal.jaljal.contract.BaseView
import com.thejaljal.jaljal.sns.Facebook
import com.thejaljal.jaljal.sns.Kakao
import com.thejaljal.jaljal.sns.Naver
import com.thejaljal.jaljal.view.SlideTouchEvent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_menu.*
import test.ym.kotilnpj.utils.RecycleUtils
import kotlinx.android.synthetic.main.include_titlebar.*
import test.ym.kotilnpj.utils.DebugUtils


/**
 * Created by no.1 on 2017-08-30.
 */

abstract class CommonActivity<in VIEW : BaseView, P: BasePresenter<VIEW>> : LifecycleActivity(), BaseView {


    companion object {
        var mPosition = 0
        var isNextPage = false
    }

    private val TAG = javaClass.simpleName
    protected var presenter: P? = null
        private set
    abstract fun onCreatePresenter(): P?

    var slideLayout: ViewGroup? = null





    protected val deviceWidth by lazy {
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        dm.widthPixels
    }

    protected val deviceHeight by lazy{
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        dm.heightPixels
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CommonApplication.currentActivity = this
        presenter = onCreatePresenter()
        presenter?.attachView(this as VIEW)


    }



    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
        RecycleUtils.recursiveRecycle(window.decorView)
        System.gc()
    }



    override fun setToast(msgInt: Int) {
        if(msgInt > - 1)Toast.makeText(this, msgInt, Toast.LENGTH_SHORT).show()
    }

    override fun setToast(msgStr: String) {
        if(msgStr != null)Toast.makeText(this, msgStr, Toast.LENGTH_SHORT).show()
    }


    fun setTitlebarView(slideLayout: ViewGroup){
        this.slideLayout = slideLayout
        titlebar_ll.setOnTouchListener(SlideTouchEvent(this, slideLayout))
    }
    fun setTitleTv(title: String){
        title_tv.text = title
    }


    fun slideInAnim(){

        if(slideLayout?.visibility == View.GONE){
            slideLayout?.visibility = View.VISIBLE
            slideLayout?.animation = AnimationUtils.loadAnimation(this, R.anim.slide_up_in)
        }
    }

    fun slideOutAnim(){
        if(slideLayout?.visibility == View.VISIBLE){
            slideLayout?.visibility = View.GONE
            val anim = AnimationUtils.loadAnimation(this, R.anim.slide_down_out)
            anim.setAnimationListener(object: Animation.AnimationListener{
                override fun onAnimationRepeat(p0: Animation?) {
                }

                override fun onAnimationEnd(p0: Animation?) {
                    slideLayout?.scrollY = 0
                }

                override fun onAnimationStart(p0: Animation?) {
                }

            })
            slideLayout?.animation = anim
        }
    }


    override fun onBackPressed() {
        if(slideLayout?.visibility == View.GONE){
            slideInAnim()
            return
        }
        super.onBackPressed()
    }


    fun menuClick(v: View){
        var intent: Intent? = null

        when(v){
            menu_tv6 ->{
                intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse("http://www.thejaljal.com/settingPlan.do"))
            }
            menu_tv4 -> intent = Intent(MainActivity@this, VisionActivity::class.java)
            menu_tv5 -> intent = Intent(MainActivity@this, FaqActivity::class.java)
            menu_tv7 -> logout()
            menu_tv8 -> intent = Intent(MainActivity@this, RecycleActivity::class.java)

            close_iv -> {
                slideInAnim()
                return
            }
        }

        val position = Integer.parseInt(v.tag.toString())
        nextPage(intent, position)

    }

    fun logout(){
        Naver.logout(this)
        Kakao.logout(object : LogoutResponseCallback() {
            override fun onCompleteLogout() {

            }
        })
        Facebook.logout()

        CookieSyncManager.createInstance(this)
        val cm = CookieManager.getInstance()
        cm.removeAllCookie()

        finish()
        startActivity(Intent(this, LoginActivity::class.java))

    }



    open fun nextPage(intent: Intent?, position: Int){

        if(position < 3){
            finish()
        }else{
            intent?.let {
                if (position == mPosition){
                    slideInAnim()
                }else{

                    isNextPage = true
                    startActivity(it)
                    finish()
                }
            }
        }


        mPosition = position

    }


    fun showMenu(v:View){
        slideOutAnim()
    }

}
