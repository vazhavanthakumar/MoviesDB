package com.android.baselib.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.android.baselib.presenter.ipresenter.IPresenter
import com.baseproject.util.CodeSnippet
import com.android.baselib.util.CustomException
import com.android.baselib.util.rxpermissions.RxPermissions
import com.android.baselib.util.sharepreference.SharedPrefManager
import com.android.baselib.view.iview.IView
import com.android.baselib.view.widgets.CustomProgressBar

abstract class BaseFragment<VB : ViewDataBinding, IP : IPresenter> : androidx.fragment.app.Fragment(),
    IView {

    protected var viewDataBinding: VB? = null
    protected var iPresenter: IP? = null
    protected var mCodeSnippet: CodeSnippet? = null
    abstract fun getLayoutId(): Int
    abstract fun onInitializePresenter(): IP
    protected var customProgressBar: CustomProgressBar? = null
    protected var mSharedPrefManager: SharedPrefManager? = null

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate<VB>(inflater, getLayoutId(), container, false)
        iPresenter = onInitializePresenter()
        iPresenter?.onCreate(arguments)

        return viewDataBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onPresenterCreated()
    }

    override fun onPause() {
        super.onPause()
        iPresenter?.onPause()
    }

    override fun doubleTapExit() {
        (activity as? IView)?.doubleTapExit()
   }

    override fun onStart() {
        super.onStart()
        iPresenter?.onStart()
    }

    override fun onResume() {
        super.onResume()
        iPresenter?.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        iPresenter?.onDestroy()
    }

    override fun showProgressbar() {
        getProgressBar()!!.show()
    }

    override fun dismissProgressbar() {
        getProgressBar()!!.dismiss()
    }

    override fun showNetworkMessage() {
        (activity as? IView)?.showNetworkMessage()
    }

    override fun openWebLink(url: String) {
        (activity as? IView)?.openWebLink(url)
    }
    override fun navigateTo(
        bundle: Bundle?,
        className: String,
        transitionOption: Bundle?
    ) {
        (activity as? IView)?.navigateTo(bundle, className, transitionOption)
    }

    override fun showMessage(message: String?) {
        if (message.isNullOrBlank().not())
            (activity as? IView)?.showMessage(message)
    }


    override fun makeCall(phoneNumber: String) {
        if (activity != null) {
            (activity as IView).makeCall(phoneNumber)
        }
    }

    override fun navigateNew(bundle: Bundle?, className: String, transitionOption: Bundle?) {
        if (activity != null) {
            (activity as IView).navigateNew(bundle, className, transitionOption)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        iPresenter?.onActivityResult(requestCode, resultCode, data)

    }

    override fun showMessage(resId: Int) {
        (activity as? IView)?.showMessage(context?.resources?.getString(resId))
    }

    private fun getProgressBar(): CustomProgressBar? {

        if (customProgressBar == null)
            customProgressBar = CustomProgressBar(requireContext())

        return customProgressBar
    }


    override val codeSnippet: CodeSnippet
        get() {
            if (mCodeSnippet == null) {
                mCodeSnippet = CodeSnippet(activity!!)
                return mCodeSnippet!!
            }
            return mCodeSnippet as CodeSnippet
        }

    override val sharedPrefManager: SharedPrefManager
        get() {
            if (mSharedPrefManager == null) {
                mSharedPrefManager = SharedPrefManager(activity?.applicationContext!!)
            }
            return mSharedPrefManager as SharedPrefManager
        }

    override fun showMessage(e: Throwable) {
        (activity as? IView)?.showMessage(e)
    }

    override fun showAlerterMessage(message: String?) {
        (activity as? IView)?.showAlerterMessage(message)
    }

    override fun showAlerterMessage(resId: Int) {
        (activity as? IView)?.showAlerterMessage(resId)
    }

    override fun showAlerterMessage(e: CustomException) {
        (activity as? IView)?.showAlerterMessage(e)
    }

    override fun onPresenterCreated() {

    }

    override fun showSnackBar(message: String) {
        (activity as? IView)?.showSnackBar(message)
    }

    override fun showSnackBar(view: View, message: String) {
        (activity as? IView)?.showSnackBar(view, message)
    }

    override fun showForceLogoutDialog() {
        (activity as? IView)?.showForceLogoutDialog()
    }

    override fun onBackPress() {
        (activity as? IView)?.onBackPress()
    }



    fun getRxPermissions(): RxPermissions {
        return RxPermissions(activity!!)
    }



}