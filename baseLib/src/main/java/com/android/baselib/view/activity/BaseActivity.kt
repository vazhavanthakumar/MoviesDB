package com.android.baselib.view.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.android.baselib.common.Constants.HttpErrorMessage.Companion.INTERNAL_SERVER_ERROR
import com.android.baselib.common.Constants.Package.Companion.APP
import com.android.baselib.model.dto.response.ErrorResponse
import com.android.baselib.presenter.ipresenter.IPresenter
import com.baseproject.util.CodeSnippet
import com.android.baselib.util.CustomException
import com.android.baselib.util.rxpermissions.RxPermissions
import com.android.baselib.util.sharepreference.SharedPrefManager
import com.baselib.R
import com.android.baselib.view.iview.IView
import com.android.baselib.view.widgets.CustomProgressBar
import com.google.android.material.snackbar.Snackbar
import java.util.*


abstract class BaseActivity<B : ViewDataBinding, T : IPresenter> : AppCompatActivity(), IView {

    protected var TAG = javaClass.simpleName
    protected var mParentView: View? = null
    protected var mCodeSnippet: CodeSnippet? = null
    protected var iPresenter: T? = null
    protected var bViewDataBinding: B? = null
    private var mCustomProgressbar: CustomProgressBar? = null
    protected var mSharedPrefManager: SharedPrefManager? = null

    private lateinit var forceLogoutDialog: AlertDialog /*SweetAlertDialog*/
    
    private var doubleBackToExitPressedOnce = false
    
    private var calender: Calendar? = null


    fun getRxPermissions(): RxPermissions {
        return RxPermissions(this)
    }

    private val progressBar: CustomProgressBar
        get() {
            if (mCustomProgressbar == null) {
                mCustomProgressbar = CustomProgressBar(this)
            }
            return mCustomProgressbar!!
        }

    override fun getActivity(): FragmentActivity {
        return this@BaseActivity
    }

    override val codeSnippet: CodeSnippet
        get() {
            if (mCodeSnippet == null) {
                mCodeSnippet = CodeSnippet(this.getActivity())
                return mCodeSnippet!!
            }
            return mCodeSnippet as CodeSnippet
        }

    override val sharedPrefManager: SharedPrefManager
        get() {
            if (mSharedPrefManager == null) {
                mSharedPrefManager = SharedPrefManager(applicationContext)
            }
            return mSharedPrefManager as SharedPrefManager
        }


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        iPresenter = onInitializePresenter()
        iPresenter!!.onCreate(intent.extras)
        if (iPresenter != null) {
            onPresenterCreated()
        }

        /*val connectionLiveData = InternetConnectionObserver(this)
         connectionLiveData.observe(this, androidx.lifecycle.Observer { it ->
             onConnectionChange(it)
         })*/
    }

    override fun onPresenterCreated() {

    }

    abstract fun getLayoutId(): Int


    abstract fun onInitializePresenter(): T


    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        mParentView = window.decorView.findViewById(android.R.id.content)
        return super.onCreateView(name, context, attrs)
    }


    override fun onStart() {
        super.onStart()
        if (iPresenter != null) iPresenter!!.onStart()
    }

    override fun onStop() {
        super.onStop()
        if (iPresenter != null) iPresenter!!.onStop()
    }

    override fun onPause() {
        super.onPause()
        if (iPresenter != null) iPresenter!!.onPause()
    }

    override fun onResume() {
        super.onResume()
        if (iPresenter != null) iPresenter!!.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (iPresenter != null) iPresenter!!.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (iPresenter != null) {
            iPresenter?.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun showMessage(message: String?) {
        message?.let { Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show() }
    }

    override fun showMessage(resId: Int) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(e: Throwable) {
        try {
            val exception = e as CustomException
            if (exception.exception != null) {

                val errorMessage =
                    CodeSnippet(this).getObjectFromJsonString(
                        exception.exception,
                        ErrorResponse::class.java
                    )

                if (errorMessage?.message != null)
                    showMessage(errorMessage.message)
                else
                    showMessage(INTERNAL_SERVER_ERROR)
            } else
                showMessage(INTERNAL_SERVER_ERROR)
        } catch (e: java.lang.Exception) {
            showMessage(INTERNAL_SERVER_ERROR)
        }

    }

    override fun showAlerterMessage(message: String?) {
        /* Alerter.create(this)
                 .setText(message)
                 .setBackgroundDrawable(null)
                 .setBackgroundColorRes(R.color.white)
                 .setTextColor(R.color.colorAccent)
                 .setDuration(1000)
                 .hideIcon()
                 .show()
         }*/
    }

    override fun showAlerterMessage(resId: Int) {

        /*Alerter.create(this)
            .setText(getString(resId))
            .setBackgroundDrawable(null)
            .setBackgroundColorRes(R.color.white)
            .setTextColor(R.color.colorAccent)
            .setDuration(1000)
            .hideIcon()
            .show()*/

    }

    override fun showAlerterMessage(e: CustomException) {

        /* Alerter.create(this)
             .setText(e.exception)
             .setBackgroundDrawable(null)
             .setBackgroundColorRes(R.color.white)
             .setTextColor(R.color.colorAccent)
             .setDuration(1000)
             .hideIcon()
             .show()*/

    }

    override fun showForceLogoutDialog() {
/*
        val logoutIntent = Intent(this@BaseActivity, Splas::class.java)
        logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        logoutIntent.putExtra(Constants.BundleKey.CLEAR_SHARED_PREFERENCES, false)
        forceLogoutDialog.dismiss()
        startActivity(logoutIntent)
        finish()*/


        /*  forceLogoutDialog = SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
          forceLogoutDialog.setTitleText(resources.getString(R.string.un_authorized_logout))
              .setContentText(resources.getString(R.string.un_authorized_logout_desc))
              .setConfirmText(resources.getString(R.string.dialog_ok))
              .showCancelButton(false)
              .setCustomImage(R.drawable.ic_warning)
              .setConfirmClickListener {
                  val logoutIntent = Intent(this@BaseActivity, LoginAgentActivity::class.java)
                  logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                  logoutIntent.putExtra(Constants.BundleKey.CLEAR_SHARED_PREFERENCES, false)
                  forceLogoutDialog.dismiss()
                  startActivity(logoutIntent)
                  finish()
              }
              .show()*/
    }


    override fun showProgressbar() {
        progressBar.show()
    }

    override fun dismissProgressbar() {
        runOnUiThread {
            try {
                progressBar.dismissProgress()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun showSnackBar(message: String) {
        if (mParentView != null) {
            val snackbar = Snackbar.make(mParentView!!, message, Snackbar.LENGTH_LONG)
            snackbar.setActionTextColor(Color.RED)
            snackbar.show()
        }
    }

    override fun showSnackBar(view: View, message: String) {
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        snackBar.setActionTextColor(Color.RED)
        snackBar.show()
    }


    override fun showNetworkMessage() {
        if (mParentView != null) {
            val snackBar = Snackbar.make(mParentView!!, "No Network found!", Snackbar.LENGTH_LONG)
            snackBar.setActionTextColor(Color.RED)
            snackBar.setAction(R.string.action_settings) { mCodeSnippet!!.showNetworkSettings() }
            snackBar.show()
        }
    }

    override fun navigateTo(
        bundle: Bundle?,
        className: String,
        transitionOption: Bundle?
    ) {
        val i = Intent()
        i.setClassName(APP, className)

        if (bundle != null)
            i.putExtras(bundle)
        startActivity(i, transitionOption)
    }


    override fun openWebLink(url: String) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    override fun doubleTapExit() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        showMessage(getString(R.string.alert_double_tap_exit))
        android.os.Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
    
    override fun onBackPress() {
        onBackPressed()
    }

    override fun makeCall(phoneNumber: String) {
        if (phoneNumber.isNotEmpty()) {
            try {
                startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber")))
            } catch (e: Exception) {
                if (e.message != null)
                    showMessage(e.message)
            }
        }
    }

    override fun navigateNew(bundle: Bundle?, className: String, transitionOption: Bundle?) {
        val i = Intent()
        i.setClassName(APP, className)

        if (bundle != null)
            i.putExtras(bundle)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(i, transitionOption)
    }

    fun replaceFragment(layoutId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(layoutId, fragment).commit()
    }


}
