package com.thejaljal.jaljal.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.*

/**
 * Created by no.1 on 2018-01-02.
 */


class MessageActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout{
            padding = dip(30)
        }
    }

}