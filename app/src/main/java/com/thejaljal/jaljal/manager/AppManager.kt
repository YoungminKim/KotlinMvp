package test.ym.kotilnpj.manager

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.telephony.TelephonyManager

/**
 * Created by no.1 on 2017-08-25.
 */

class AppManager(){

    companion object {
        @JvmStatic fun isNetworkConnection(ctx: Context):Boolean{
            val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val ni:NetworkInfo? = cm.getActiveNetworkInfo()
            ni.let {
                if(ni?.getType()== ConnectivityManager.TYPE_WIFI) return true
                else if(ni?.getType() == ConnectivityManager.TYPE_MOBILE)return true
            }
            return false
        }

        @JvmStatic fun isServiceRunning(ctx : Context, serviceName: String):Boolean{
            val manager = ctx.getSystemService(Activity.ACTIVITY_SERVICE) as ActivityManager
            for(service:ActivityManager.RunningServiceInfo in manager.getRunningServices(Int.MAX_VALUE)){
                if(serviceName.equals(service.service.className)) return true
            }

            return false
        }

        @JvmStatic fun getAppVerName(ctx: Context):String?{
            try {
                var pi = ctx.packageManager.getPackageInfo(ctx.packageName, 0)
                return pi.versionName
            }catch (e: PackageManager.NameNotFoundException){
                e.printStackTrace()
            }
            return null
        }

        @JvmStatic fun getAppVerCode(ctx: Context):Int?{
            try {
                var pi = ctx.packageManager.getPackageInfo(ctx.packageName, 0)
                return pi.versionCode
            }catch (e: PackageManager.NameNotFoundException){
                e.printStackTrace()
            }
            return 0
        }


        @JvmStatic fun getIMEI(ctx: Context):String?{
            return  (ctx.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).deviceId
        }

        @JvmStatic fun isAppInstalled (ctx: Context, packName:String):Boolean{
            try {
                val pi: PackageInfo? = ctx.packageManager.getPackageInfo(packName, 0)
                if(pi != null)return true
            }catch (e: PackageManager.NameNotFoundException){
                e.printStackTrace()
            }
            return false
        }

        @JvmStatic fun getLauncherClassName(ctx: Context): String?{
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            val pm = ctx.getPackageManager()
            val resolveInfos = pm.queryIntentActivities(intent, 0)
            for (resolveInfo in resolveInfos) {
                val pName = resolveInfo.activityInfo.applicationInfo.packageName
                if (pName == ctx.getPackageName()) {
                    return resolveInfo.activityInfo.name
                }
            }

            return null
        }

        @JvmStatic fun setAppBadge(ctx: Context, count: Int){
            val intent = Intent("android.intent.action.BADGE_COUNT_UPDATE")
            intent.putExtra("badge_count", count);
            intent.putExtra("badge_count_package_name", ctx.getPackageName())
            intent.putExtra("badge_count_class_name", getLauncherClassName(ctx))
            ctx.sendBroadcast(intent)
        }

    }


}