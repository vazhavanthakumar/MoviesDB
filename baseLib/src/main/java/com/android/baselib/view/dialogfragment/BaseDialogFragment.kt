package com.android.baselib.view.dialogfragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.android.baselib.presenter.ipresenter.IPresenter
import com.baseproject.util.CodeSnippet
import com.android.baselib.util.CustomException
import com.android.baselib.util.sharepreference.SharedPrefManager
import com.android.baselib.view.iview.IView

abstract class BaseDialogFragment <B : ViewDataBinding, T : IPresenter> : DialogFragment(), IView {


    protected var TAG = javaClass.simpleName
    protected var rootView: View? = null
    protected var iPresenter: T? = null
    private var layoutRes = -1
    lateinit var bViewDataFragmentBinding: B

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bViewDataFragmentBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        bViewDataFragmentBinding.root
        iPresenter = initializePresenter()
        iPresenter?.onCreate(this.arguments)
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        if (iPresenter != null) {
            onPresenterCreated()
        }
        return bViewDataFragmentBinding.root
    }

    override fun onPresenterCreated() {

    }

    abstract fun initializePresenter(): T

    abstract fun getLayoutId(): Int

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window?.setLayout(width, height)
        }
    }


    override fun onResume() {
        super.onResume()
        if (iPresenter != null) iPresenter!!.onResume()
    }




    override fun onBackPress() {
        if (activity != null) {
            (activity as IView).onBackPress()
        }
    }

    override fun doubleTapExit() {
        if (activity != null) {
            (activity as IView).doubleTapExit()
        }
    }

    override fun showForceLogoutDialog() {
        try {
            if (activity as IView? != null) {
                (activity as IView).showForceLogoutDialog()
            }
        } catch (e: ClassCastException) {

        }
    }

   override fun openWebLink(url: String) {
        if (activity != null) {
            (activity as IView).openWebLink(url)
        }
    }
    override fun showMessage(message: String?) {
        if (activity != null) {
            (activity as IView).showMessage(message)
        }
    }

    override fun showMessage(resId: Int) {
        if (activity != null) {
            (activity as IView).showMessage(resId)
        }
    }

    override fun showMessage(e: Throwable) {
        if (activity != null) {
            (activity as IView).showMessage(e)
        }
    }

    override fun showAlerterMessage(message: String?) {
        if (activity != null) {
            (activity as IView).showAlerterMessage(message)
        }
    }

    override fun showAlerterMessage(resId: Int) {
        if (activity != null) {
            (activity as IView).showAlerterMessage(resId)
        }
    }

    override fun showAlerterMessage(e: CustomException) {
        if (activity != null) {
            (activity as IView).showAlerterMessage(e)
        }
    }

    override fun showProgressbar() {
        if (activity != null) {
            (activity as IView).showProgressbar()
        }
    }


    override fun dismissProgressbar() {
        if (activity != null) {
            (activity as IView).dismissProgressbar()
        }
    }



    override fun showNetworkMessage() {
        if (activity != null) {
            (activity as IView).showNetworkMessage()
        }
    }



    override val codeSnippet: CodeSnippet
        get() = (activity as IView).codeSnippet

    override val sharedPrefManager: SharedPrefManager
        get() = (activity as IView).sharedPrefManager


    override fun showSnackBar(message: String) {
        if (activity != null) {
            (activity as IView).showSnackBar(message)
        }
    }


    override fun navigateTo(
        bundle: Bundle?,
        className: String,
        transitionOption: Bundle?
    ) {
        if (activity != null) {
            (activity as IView).navigateTo(bundle, className, transitionOption)
        }
    }

    override fun showSnackBar(view: View, message: String) {
        if (activity != null) {
            (activity as IView).showSnackBar(view,message)
        }
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


    fun getFragment(): Fragment {
        return this
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (iPresenter != null) {
            data?.let { iPresenter?.onActivityResult(requestCode, resultCode, it) }
        }

    }




}