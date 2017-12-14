package com.thejaljal.jaljal.model

/**
 * Created by no.1 on 2017-11-28.
 */

data class Faq(val result: Boolean?, val message: String?, val data: Data?){
    data class Data(val faqList: ArrayList<Board>)
    data class Board(var boardSeq: Int = 0,
                     var boardCat: String?,
                     var boardSj: String?,
                     var boardCn: String?)
}