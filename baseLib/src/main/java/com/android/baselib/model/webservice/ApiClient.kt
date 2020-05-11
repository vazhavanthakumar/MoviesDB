package com.android.baselib.model.webservice

import android.content.Context
import com.android.baselib.common.Constants.ApiHeaderKey.Companion.AUTHORIZATION
import com.android.baselib.common.Constants.ApiHeaderKey.Companion.CONTENT_TYPE
import com.android.baselib.common.Constants.ApiHeaderKey.Companion.USER_TYPE
import com.android.baselib.common.Constants.ApiHeaderKey.Companion.applicationJson
import com.android.baselib.util.sharepreference.SharedPrefManager
import com.baselib.BuildConfig
import com.baselib.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


class ApiClient(var context: Context) {

    private var sharedPrefManager: SharedPrefManager? = null

    init {
        sharedPrefManager = SharedPrefManager(context)
    }


    fun getClient(): Retrofit {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY   // set your desired log level

        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.connectTimeout(2, TimeUnit.MINUTES) //Connection time out set limit
        httpClient.readTimeout(2, TimeUnit.MINUTES)  //Connection read time out set limit

        /*
        * If   ==> token not empty , it will be shared in the header
        * Else ==> It will not add in the header
        * */
        if (!sharedPrefManager?.token.isNullOrEmpty()) {
            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header(AUTHORIZATION, sharedPrefManager?.token!!)
                    .header(USER_TYPE, "3")
                    .header(CONTENT_TYPE, applicationJson)
                    .method(original.method(), original.body())
                    .build()
                chain.proceed(request)
            }
        }else{
            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
//                    .header(USER_TYPE, "3")
                    .header(CONTENT_TYPE, applicationJson)
                    .method(original.method(), original.body())
                    .build()
                chain.proceed(request)
            }
        }
        // addItem your other interceptors …
        // addItem logging as last interceptor
        if (BuildConfig.DEBUG)
            httpClient.addInterceptor(logging)  // <-- this is the important line!
        return Retrofit.Builder()
            .baseUrl(context.getString(R.string.base_url))
            .client(httpClient.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create()) /*Use can mention your desired parser over here.!*/
            .build()
    }


}
