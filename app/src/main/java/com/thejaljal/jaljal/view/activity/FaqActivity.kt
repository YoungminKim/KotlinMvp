package com.thejaljal.jaljal.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.contract.activity.FaqContract
import com.thejaljal.jaljal.contract.activity.FaqPresenter
import com.thejaljal.jaljal.view.adapter.FaqAdapter
import kotlinx.android.synthetic.main.activity_faq.*

/**
 * Created by no.1 on 2017-11-28.
 */

class FaqActivity: CommonActivity<FaqContract.View, FaqPresenter>(), FaqContract.View{
    override fun onCreatePresenter() = FaqPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)

        val adapter = FaqAdapter(this)
        rv.adapter = adapter

        presenter?.run{
            getFaqList()
            setFaqAdapterModel(adapter)
            setFaqAdapterView(adapter)
        }




    }

    fun goContract(view: View?){
        startActivity(Intent(this, ContactUsActivity::class.java))
    }

}