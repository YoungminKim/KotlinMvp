package com.thejaljal.jaljal.view.activity

import android.os.Bundle
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.contract.activity.PremiumItemContract
import com.thejaljal.jaljal.contract.activity.PremiumItemPresenter
import com.thejaljal.jaljal.model.RecipeCalendar
import com.thejaljal.jaljal.view.adapter.PremiumItemAdapter
import kotlinx.android.synthetic.main.activity_premiumlist.*
import test.ym.kotilnpj.utils.DebugUtils

/**
 * Created by no.1 on 2017-12-11.
 */

class PremiumItemActivity : CommonActivity<PremiumItemContract.View, PremiumItemPresenter>(), PremiumItemContract.View{


    val TAG = javaClass.simpleName

    var isOrder: Boolean = false



    override fun onCreatePresenter() = PremiumItemPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_premiumlist)



        val adapter = PremiumItemAdapter(this)
        presenter?.run {
            setExtra(intent)
            adapterView = adapter
            adapterModel = adapter
            getPremiumList()
        }
        rv.adapter = adapter


    }


    override fun setIsOrderView(isOrder: Boolean) {
        apply_tv?.run {
            if(isOrder) text = "주문 취소하기"
            else text = "신청하기"

            setOnClickListener {
                if(isOrder){
                    presenter?.checkModifyStatus()
                }else{

                }
            }
        }
    }

}
