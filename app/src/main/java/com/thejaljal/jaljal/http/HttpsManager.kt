package test.ym.kotilnpj.http

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.security.GeneralSecurityException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

/**
 * Created by no.1 on 2017-08-25.
 */

open class HttpsManager{

    companion object {

        val CONNECTION_TIMEOUT:Long = 30
        val WRITE_TIMEOUT:Long =15
        val READ_TIMEOUT:Long = 15

        @JvmStatic val service: HttpService = setService()
        @JvmStatic lateinit var client: OkHttpClient

        @JvmStatic fun setService(): HttpService {

            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val cookieManager = CookieManager()
            cookieManager.setCookiePolicy (CookiePolicy.ACCEPT_ALL)

            if(UrlInfo.SSL)client = configureClient(OkHttpClient().newBuilder())
                    .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .build()


            val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(UrlInfo.HEADER + UrlInfo.API_HEADER)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(HttpService::class.java)


        }


        @JvmStatic fun configureClient(builder: OkHttpClient.Builder): OkHttpClient .Builder{
            val certs = arrayOf<TrustManager>(object : X509TrustManager{
                override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun getAcceptedIssuers(): Array<X509Certificate>? {
                    return null
                }

            })
            var ctx:SSLContext? = null

            try {
                ctx = SSLContext.getInstance("TLS")
                ctx.init(null, certs, SecureRandom())
            }catch (e:GeneralSecurityException){
                e.printStackTrace()
            }

            try {
                val hostNameVerifier = HostnameVerifier { s, sslSession -> true }
                builder.sslSocketFactory(ctx!!.socketFactory).hostnameVerifier(hostNameVerifier)
            }catch (e:Exception){
                e.printStackTrace()
            }

            return builder
        }
    }

}

