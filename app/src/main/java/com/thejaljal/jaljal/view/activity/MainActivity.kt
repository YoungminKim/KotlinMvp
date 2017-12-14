package com.thejaljal.jaljal.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.contract.activity.MainContract
import com.thejaljal.jaljal.contract.activity.MainPresenter
import com.thejaljal.jaljal.view.SlideTouchEvent
import com.thejaljal.jaljal.view.adapter.MainAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_menu.*
import test.ym.kotilnpj.utils.DebugUtils

/**
 * Created by no.1 on 2017-08-30.
 */

class MainActivity : CommonActivity<MainContract.View, MainPresenter>(), MainContract.View, View.OnClickListener{
    private val TAG = javaClass.simpleName

    private lateinit var adapter: MainAdapter
    lateinit var tabs:ArrayList<TextView>

    override fun onCreatePresenter(): MainPresenter? = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabs = arrayListOf<TextView>(tab_tv0, tab_tv1, tab_tv2)
        slideLayout = main_fl
        vp.adapter = MainAdapter(this, supportFragmentManager)

        vp.run{

            offscreenPageLimit = 3
            addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

                override fun onPageSelected(position: Int) {
                    //tabs[position].setText()
                }
            })

            for(i in 0..tabs.size - 1){
                tabs.get(i).setOnClickListener{
                    setCurrentItem(i)
                }
            }
        }



        main_title_ll.setOnTouchListener(SlideTouchEvent(this, main_fl))

    }

    override fun onResume() {
        super.onResume()

        if(isNextPage){
            vp.setCurrentItem(mPosition)
            slideInAnim()
        }
        isNextPage = false
    }

    override fun onPause() {
        super.onPause()
    }


    override fun onClick(v: View?) {
        var intent: Intent? = null
        when(v){
            menu_iv -> slideOutAnim()
        }

        intent?.let { startActivity(intent) }
    }

    override fun onBackPressed() {
        if(main_fl.visibility == View.GONE){
            slideInAnim()
            return
        }
        super.onBackPressed()
    }

    override fun nextPage(intent: Intent?, position: Int) {
        if(intent != null && position > 2){
            isNextPage = true
            startActivity(intent)
        }else{
            vp.setCurrentItem(position)
            slideInAnim()
        }

        mPosition = position

    }
}

