package com.android.baselib.view.iview

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.baseproject.util.CodeSnippet
import com.android.baselib.util.CustomException
import com.android.baselib.util.sharepreference.SharedPrefManager

interface IView  {
    fun showMessage(message: String?)

    fun showMessage(resId: Int)

    fun showMessage(e: Throwable)

    fun showAlerterMessage(message: String?)

    fun showAlerterMessage(resId: Int)

    fun showAlerterMessage(e: CustomException)

    fun onPresenterCreated()

    fun showProgressbar()

    fun dismissProgressbar()

    fun getActivity(): FragmentActivity?

    fun showSnackBar(message: String)

    fun showSnackBar(view: View, message: String)

    fun showNetworkMessage()

    fun showForceLogoutDialog()

    val codeSnippet: CodeSnippet

    val sharedPrefManager: SharedPrefManager

    fun onBackPress()
    fun openWebLink(url:String)

    fun onConnectionChange(isConnected: Boolean){}

fun doubleTapExit()
    fun makeCall(phoneNumber:String)

    fun navigateNew(
        bundle: Bundle?,
        className: String,
        transitionOption: Bundle?
    )
    fun navigateTo(
        bundle: Bundle?,
        className: String,
        transitionOption: Bundle?
    )
}