package com.thejaljal.jaljal.contract.adapter

import com.thejaljal.jaljal.contract.BaseView
import com.thejaljal.jaljal.model.Faq

/**
 * Created by no.1 on 2017-11-28.
 */

interface FaqAdapterContract{
    interface View: BaseView {
        fun notifyAdapter()
    }

    interface Model{
        val list: ArrayList<Faq.Board>
        fun addData(data: ArrayList<Faq.Board>)
    }
}