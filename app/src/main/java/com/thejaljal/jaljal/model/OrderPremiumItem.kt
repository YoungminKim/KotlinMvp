package com.thejaljal.jaljal.model

/**
 * Created by no.1 on 2017-12-22.
 */

data class OrderPremiumItem(val result:Boolean?, val message:String?, val data:Data?){
    data class Data(val addtOrderSeq: String, val deliveryDate: String)
}