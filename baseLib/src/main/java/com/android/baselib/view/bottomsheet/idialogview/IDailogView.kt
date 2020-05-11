package com.android.baselib.view.bottomsheet.idialogview

import android.view.View
import com.baseproject.util.CodeSnippet
import com.android.baselib.util.CustomException
import com.android.baselib.util.sharepreference.SharedPrefManager


interface IDailogView {

    fun showMessage(message: String)

    fun showMessage(resId: Int)

    fun showMessage(e: CustomException)

    fun showSnackBar(message: String)

    fun showSnackBar(view: View, message: String)

    fun showNetworkMessage()

    val codeSnippet: CodeSnippet

    val sharedPrefManager: SharedPrefManager

    fun onViewCreated()
}