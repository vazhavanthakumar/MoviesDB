package com.android.baselib.presenter

import android.content.Intent
import com.android.baselib.presenter.ipresenter.IPresenter
import com.baseproject.util.CodeSnippet
import com.android.baselib.util.sharepreference.SharedPrefManager
import com.android.baselib.view.iview.IView


abstract class BasePresenter<T : IView>(protected var iView: T?) : IPresenter {

    protected var TAG = javaClass.simpleName


    override fun onStart() {
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

    }


    override fun isInternetConnected(): Boolean {
        return iView?.codeSnippet?.hasNetworkConnection()!!
    }


    override fun getCodeSnippet(): CodeSnippet {
        return iView?.codeSnippet!!
    }

    override fun showNetworkUnavailable() {
        iView?.showNetworkMessage()
    }

    override fun getSharedPrefManager(): SharedPrefManager {
        return iView?.sharedPrefManager!!
    }

    override fun logoutUser() {
        iView?.showForceLogoutDialog()
    }

    override fun showRetryOption() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissProgressbar() {
        iView?.dismissProgressbar()
    }


}