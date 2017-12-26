package com.thejaljal.jaljal.view.activity

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.common.DateStrReplace
import com.thejaljal.jaljal.contract.activity.OrderPremiumItemContract
import com.thejaljal.jaljal.contract.activity.OrderPremiumItemPresenter
import com.thejaljal.jaljal.utils.CurrencyUnit
import com.thejaljal.jaljal.view.dialog.StringDialog
import kotlinx.android.synthetic.main.activity_order_premium_item.*


/**
 * Created by no.1 on 2017-12-19.
 */

class OrderPremiumItemActivity: CommonActivity<OrderPremiumItemContract.View, OrderPremiumItemPresenter>(), OrderPremiumItemContract.View{



    override fun onCreatePresenter() = OrderPremiumItemPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_premium_item)

        presenter?.run {
            setExtras(intent)
            getUserInfo()
        }
    }

    override fun setPlanPrice(plan: String, price: Int) {
        plan_name_tv.text = plan
        plan_price_tv.text = CurrencyUnit.getWon(price)
    }

    override fun setTotalPrice(price: Int) {
        total_price_tv.text = CurrencyUnit.getWon(price)
    }

    override fun selectDate(str: String?) {
        date_tv.text = str
    }


    override fun showView() {
        if(order_ll.visibility == View.GONE){
            order_ll.visibility = View.VISIBLE
            order_ll.animation = AnimationUtils.loadAnimation(this, R.anim.slide_up_in)
        }
    }

    override fun hideView() {
        if(order_ll.visibility == View.VISIBLE){
            val ani = AnimationUtils.loadAnimation(this, R.anim.slide_down_out)
            ani.setAnimationListener(object : Animation.AnimationListener{
                override fun onAnimationRepeat(p0: Animation?) {
                }

                override fun onAnimationEnd(p0: Animation?) {
                    order_ll.visibility = View.GONE
                    finish()
                }

                override fun onAnimationStart(p0: Animation?) {
                }

            })
        }

    }

    fun onClick(view: View?){
        val dialog = StringDialog(this, presenter?.getDataStringArr())
        dialog.setOnItemClick(object : StringDialog.SelectListner{
            override fun selectListner(position: Int, str: String) {
                presenter?.setSelectPosition(position)
                selectDate(str)
            }

        })
        dialog.show()
    }

}