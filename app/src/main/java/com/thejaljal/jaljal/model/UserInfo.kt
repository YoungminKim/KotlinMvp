package com.thejaljal.jaljal.model

/**
 * Created by no.1 on 2017-12-04.
 */

data class UserInfo(val result: Boolean?, val message: String?, val data: Data?){
    data class Data(
            val deliveryNm: String,
            val prgrName: String,
            val prgrPrice: Int
    )
}