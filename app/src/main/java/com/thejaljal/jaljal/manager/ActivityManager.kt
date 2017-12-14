package test.ym.kotilnpj.manager

import android.app.Activity
import java.util.ArrayList

/**
 * Created by no.1 on 2017-08-25.
 */
class ActivityManager{
    companion object {
        @JvmStatic val actList = ArrayList<Activity>()

        @JvmStatic fun allActivityFinish(){
            for (a in actList){
                a.finish()
            }
        }

        @JvmStatic fun activityFinish(name: String){
            for (a in actList){
                if(a.javaClass.simpleName.equals(name)) {
                    actList.remove(a)
                    a.finish()
                }
            }
        }

        @JvmStatic fun allActvitiyFinish(name: String){
            for (a in actList){
                if(!a.javaClass.simpleName.equals(name)) {
                    actList.remove(a)
                    a.finish()
                }
            }
        }
    }
}