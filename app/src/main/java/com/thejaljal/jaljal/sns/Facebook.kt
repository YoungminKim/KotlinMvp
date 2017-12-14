package com.thejaljal.jaljal.sns

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import org.json.JSONObject

/**
 * Created by no.1 on 2017-09-01.
 */

object Facebook{

    private val FACEBOOK_PERMISSIONS = arrayListOf<String>("email")
    private lateinit var fbCallbackManager: CallbackManager



    interface LoginListner{
        fun onCancel()
        fun onSuccess(id: String, name: String, picture: String)
        fun onFailed(error: String)
    }


    fun login(ctx:Context, listner: LoginListner?) {
        val profile = Profile.getCurrentProfile()
        if(profile != null && profile.id.length > 0){
            listner!!.onSuccess(profile.id, profile.name, "http://graph.facebook.com/" + profile.id + "/picture?width=200&height=200")
        }else{
            fbCallbackManager = CallbackManager.Factory.create()
            LoginManager.getInstance().registerCallback(fbCallbackManager, object: FacebookCallback<LoginResult> {
                override fun onCancel() = listner!!.onCancel()

                override fun onError(error: FacebookException?) = listner!!.onFailed(error.toString())

                override fun onSuccess(result: LoginResult) {
                    val request = GraphRequest.newMeRequest(result.accessToken, object : GraphRequest.GraphJSONObjectCallback{
                        override fun onCompleted(`object`: JSONObject, response: GraphResponse?) {
                            listner!!.onSuccess(`object`.getString("id"), `object`.getString("name"), "http://graph.facebook.com/" + `object`.getString("id") + "/picture?width=200&height=200")
                        }
                    })
                    val params = Bundle()
                    params.putString("fields", "id, name, first_name, last_name")
                    request.parameters = params
                    request.executeAsync()
                }

            })
            LoginManager.getInstance().logInWithReadPermissions(ctx as Activity, FACEBOOK_PERMISSIONS)
        }
    }

    fun logout() = LoginManager.getInstance().logOut()

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent){
        fbCallbackManager.onActivityResult(requestCode, resultCode, data)
    }

}