package com.android.baselib.util.sharepreference

import android.content.Context
import com.android.baselib.common.Constants.PreferenceKey.Companion.LOGGED_IN
import com.android.baselib.common.Constants.PreferenceKey.Companion.TOKEN
import com.android.baselib.common.Constants.PreferenceKey.Companion.USER_DATA
import com.android.baselib.util.sharepreference.SharedPref
import com.baseproject.util.CodeSnippet


class SharedPrefManager(context: Context) {
    private var codeSnippet: CodeSnippet = CodeSnippet(context)
    var sharedPref: SharedPref = SharedPref(context)


    fun clearData() {
        sharedPref.clearSharedValue()
    }


    var token: String
        get() = sharedPref.getStringDefaultValue(TOKEN)
        set(token) = sharedPref.setSharedValue(TOKEN, token)

    var userData: String
        get() = sharedPref.getStringDefaultValue(USER_DATA)
        set(userData) = sharedPref.setSharedValue(USER_DATA, userData)

    var isLoggedIn: Boolean?
        get() = sharedPref.getBooleanValue(LOGGED_IN)
        set(token) = sharedPref.setSharedValue(LOGGED_IN, token)


}