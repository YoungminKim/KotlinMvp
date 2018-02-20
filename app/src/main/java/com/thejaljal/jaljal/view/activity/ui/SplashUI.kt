package com.thejaljal.jaljal.view.activity.ui

import android.support.v4.content.ContextCompat
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.view.activity.SplashActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.verticalLayout

/**
 * Created by no.1 on 2018-01-11.
 */

class SplashUI: AnkoComponent<SplashActivity>{

    override fun createView(ui: AnkoContext<SplashActivity>) = with(ui) {
        verticalLayout {
            background = ContextCompat.getDrawable(context, R.drawable.splash)
        }
    }

}