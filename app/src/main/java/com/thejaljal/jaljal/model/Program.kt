package com.thejaljal.jaljal.model

/**
 * Created by no.1 on 2017-12-06.
 */

data class Program(val result:Boolean?, val message:String?, val data:Data?) {
    data class Data(val programList: ArrayList<Program> = arrayListOf())
    data class Program(val prgrSeq: Int, val prgrName: String)
}