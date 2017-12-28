package test.ym.kotilnpj.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.thejaljal.jaljal.AppConst

/**
 * Created by no.1 on 2017-08-25.
 */

object DebugUtils{
    @JvmStatic fun setLog(TAG : String, MSG : String){
        if(AppConst.IS_TEST)Log.e(TAG, MSG)
    }

    @JvmStatic fun setToast(ctx : Context, MSG: String){
        if(AppConst.IS_TEST)Toast.makeText(ctx, MSG, Toast.LENGTH_SHORT).show()
    }

}
