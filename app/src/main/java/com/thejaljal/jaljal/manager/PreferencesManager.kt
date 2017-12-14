package test.ym.kotilnpj.manager

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

/**
 * Created by no.1 on 2017-08-25.
 */

class PreferencesManager(private val ctx: Context){

    private val pref:SharedPreferences
    private val saver:SharedPreferences.Editor

    init {
        pref = ctx.getSharedPreferences(ctx.packageName, Activity.MODE_PRIVATE)
        saver = pref.edit()
    }



    fun setAccessKey(value: String?){
        saver.putString("accessKey", value)
        saver.commit()
    }

    fun getAccessKey() = pref.getString("accessKey", null)


    fun setIsPush(value: Boolean) {
        saver.putBoolean("isPush", value)
        saver.commit()
    }

    fun isPush() = pref.getBoolean("isPush", true)


    fun setToken(value: String) {
        saver.putString("pushToken", value)
        saver.commit()
    }

    fun getToken() = pref.getString("pushToken", null)

    fun setIsLogin(value: Boolean){
        saver.putBoolean("isLogin", value)
        saver.commit()
    }

    fun isLogin() = pref.getBoolean("isLogin", false)

    fun setLastVersion(value: Int){
        saver.putInt("lastVersion", value)
        saver.commit()
    }

    fun getLastVersion() = pref.getInt("lastVersion", 0)

}