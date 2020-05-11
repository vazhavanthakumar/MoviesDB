package com.android.baselib.util

import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable

class RxUtils {
    companion object {
        @SuppressLint("CheckResult")
        fun <T, O> runInBack(process:Callable<T>, onNext:Function<T,O>, complete:Action){
            Observable.fromCallable(process)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        it?.printStackTrace()
                    }
                    .doOnNext {
                        onNext.apply(it)
                    }
                    .doOnComplete {
                        complete.run()
                    }
                    .subscribe({  }, {  it?.printStackTrace() })
        }


    }
}