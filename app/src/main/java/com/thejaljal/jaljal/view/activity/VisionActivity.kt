package com.thejaljal.jaljal.view.activity

import android.os.Bundle
import com.thejaljal.jaljal.R
import com.thejaljal.jaljal.contract.AbstractPresenter
import com.thejaljal.jaljal.contract.BaseView
import kotlinx.android.synthetic.main.activity_recipe.*

/**
 * Created by no.1 on 2017-11-24.
 */
class VisionActivity : CommonActivity<BaseView, AbstractPresenter<BaseView>>(), BaseView {
    override fun onCreatePresenter() = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vision)

        setTitlebarView(sub_ll)
        setTitleTv("잘잘 비전")
    }

}