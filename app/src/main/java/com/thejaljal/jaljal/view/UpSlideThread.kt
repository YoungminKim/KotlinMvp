package com.thejaljal.jaljal.view

import android.app.Activity
import android.view.ViewGroup

/**
 * Created by no.1 on 2017-10-18.
 */

class UpSlideThread(val act:Activity, val parent: ViewGroup): Thread(){
    var moveY:Int

    init {
        moveY = parent.scrollY
    }

    override fun run() {
        super.run()
        while (!interrupted() && moveY < 0){
            try{
                moveY += 10
                if(moveY > -10) moveY = 0

                act.runOnUiThread { parent.scrollTo(0, moveY) }
                sleep(1)
            }catch (e: InterruptedException){
                e.printStackTrace()
            }
        }
    }

}