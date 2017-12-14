package com.thejaljal.jaljal.model

import android.arch.lifecycle.AndroidViewModel

/**
 * Created by no.1 on 2017-08-30.
 */
data class Login(val result:Boolean?, val message:String?, val data:Data?) {
    data class Data(val accessKey: String?, val regDt:String?)
}