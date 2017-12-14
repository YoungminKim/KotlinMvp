package com.thejaljal.jaljal.view

import android.app.Activity
import android.graphics.PointF
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.thejaljal.jaljal.R
import kotlinx.android.synthetic.main.activity_main.*
import test.ym.kotilnpj.utils.DebugUtils

/**
 * Created by no.1 on 2017-11-20.
 */

class SlideTouchEvent(val act: Activity, val parent: ViewGroup): View.OnTouchListener{
    var mVelocityTracker: VelocityTracker? = null
    var firstY = 0

    val SNAP_VELOCITY = 100

    val mListPoint by lazy{
        PointF()
    }


    protected val deviceHeight by lazy{
        val dm = DisplayMetrics()
        act.windowManager.defaultDisplay.getMetrics(dm)
        dm.heightPixels
    }

    override fun onTouch(p0: View?, event: MotionEvent?): Boolean {
        if(mVelocityTracker == null) mVelocityTracker = VelocityTracker.obtain()
        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                firstY = event.rawY.toInt()
                mListPoint.set(event.rawX, event.rawY)
            }

            MotionEvent.ACTION_MOVE -> {
                if(event.rawY <= 0){
                    parent.scrollTo(0, 0)
                }else if(firstY < event.rawY){
                    parent.scrollBy(0, -(event.rawY - mListPoint.y).toInt())
                    mListPoint.set(event.rawX, event.rawY)
                    parent.invalidate()
                }
            }



            MotionEvent.ACTION_UP -> {
                mVelocityTracker?.computeCurrentVelocity(1000)
                var trackerY = mVelocityTracker?.yVelocity

                if(trackerY!! > SNAP_VELOCITY || deviceHeight * 0.5 < event.rawY){
                    slideOutAnim()
                }else{
                    UpSlideThread(act, parent).start()
                }
                mVelocityTracker?.recycle()
                mVelocityTracker = null
            }
        }
        return true
    }


    fun slideOutAnim(){
        if(parent.visibility == View.VISIBLE){
            parent.visibility = View.GONE
            val ani = AnimationUtils.loadAnimation(act, R.anim.slide_down_out)
            ani.setAnimationListener(object: Animation.AnimationListener{
                override fun onAnimationRepeat(p0: Animation?) {
                }

                override fun onAnimationEnd(p0: Animation?) {
                    parent.scrollTo(0, 0)
                }

                override fun onAnimationStart(p0: Animation?) {
                }

            })

            parent.animation = ani
        }
    }

}