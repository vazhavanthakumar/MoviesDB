package com.android.baselib.util.extensions

import android.annotation.SuppressLint
import com.squareup.moshi.Moshi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable


fun <T> T.moshiObjToString(type: Class<T>): String {
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter(type)
    return jsonAdapter.toJson(this)
}

fun <T> String.moshiStringToObj(type: Class<T>): T? {
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter(type)
    return jsonAdapter.fromJson(this)
}



@SuppressLint("CheckResult")
fun <T, O>  Callable<T>.runInBack(onNext: Function<T, O>, complete: Action=Action{}){
    Observable.fromCallable(this)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                it?.printStackTrace()
            }
            .doOnNext {
                //println("Next ->$it")
                onNext.apply(it)
            }
            .doOnComplete {
                complete.run()
            }
            .subscribe({

            }, {  it?.printStackTrace() })
}



/* var flowerInfo = FlowerInfo("12321321321", "test11")
      val dataString = flowerInfo.moshiObjToString(FlowerInfo::class.java)
      flowerInfo = dataString.moshiStringToObj(FlowerInfo::class.java)!!
      Timber.d("result $dataString $flowerInfo}")*/

