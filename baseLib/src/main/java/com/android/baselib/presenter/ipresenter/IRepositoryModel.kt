package com.android.baselib.presenter.ipresenter

import com.baseproject.util.CodeSnippet
import com.android.baselib.util.sharepreference.SharedPrefManager


interface IRepositoryModel {

    fun isInternetConnected(): Boolean

    fun logoutUser()

    fun getCodeSnippet(): CodeSnippet

    fun showRetryOption()

    fun showNetworkUnavailable()

    fun dismissProgressbar()

    fun getSharedPrefManager(): SharedPrefManager

}