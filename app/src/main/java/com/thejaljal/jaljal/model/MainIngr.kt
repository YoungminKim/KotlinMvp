package com.thejaljal.jaljal.model

/**
 * Created by no.1 on 2017-12-06.
 */

data class MainIngr(val result:Boolean?, val message:String?, val data:Data?) {
    data class Data(val ingrList: ArrayList<String> = arrayListOf())
}