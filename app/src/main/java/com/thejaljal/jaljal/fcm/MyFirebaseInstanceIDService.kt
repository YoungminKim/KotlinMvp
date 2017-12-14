package com.thejaljal.jaljal.fcm

import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import com.thejaljal.jaljal.model.Common
import com.thejaljal.jaljal.model.Login
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import test.ym.kotilnpj.http.HttpService
import test.ym.kotilnpj.http.HttpsManager
import test.ym.kotilnpj.http.UrlInfo
import test.ym.kotilnpj.manager.PreferencesManager
import test.ym.kotilnpj.utils.DebugUtils
import java.util.HashMap

/**
 * Created by no.1 on 2017-11-02.
 */

class MyFirebaseInstanceIDService : FirebaseInstanceIdService() {

    companion object {

        private val TAG = "MyFirebaseIIDService"
    }

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]
    override fun onTokenRefresh() {
        // Get updated InstanceID token.
        val refreshedToken = FirebaseInstanceId.getInstance().token
        DebugUtils.setLog(TAG, "Refreshed token: " + refreshedToken!!)

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.

        if (refreshedToken != null) sendRegistrationToServer(refreshedToken)
    }
    // [END refresh_token]

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private fun sendRegistrationToServer(token: String) {
        val pm = PreferencesManager(this)
        pm.setToken(token)
        val params = HashMap<String, Any>()
        val accessKey = pm.getAccessKey()
        if (accessKey != null) params.put("accessKey", accessKey)
        params.put("pushToken", token)


        CompositeDisposable().add(HttpsManager.service.updateToken(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe())

    }

}