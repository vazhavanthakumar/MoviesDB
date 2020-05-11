package com.android.baselib.common

import android.app.Application
import com.baselib.BuildConfig
import timber.log.Timber


class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
