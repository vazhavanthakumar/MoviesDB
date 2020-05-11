package com.android.baselib.view.bottomsheet

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.baseproject.util.CodeSnippet
import com.android.baselib.util.CustomException
import com.android.baselib.util.sharepreference.SharedPrefManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.android.baselib.view.bottomsheet.idialogview.IDailogView
import com.baselib.R


abstract class BaseBottomSheetDialog<B : ViewDataBinding>(context: Context) : BottomSheetDialog(context) ,
    IDailogView {

    protected var TAG = javaClass.simpleName
    protected var bViewDataBinding: B? = null
    protected var mCodeSnippet: CodeSnippet? = null
    protected var mSharedPrefManager: SharedPrefManager? = null

    abstract fun bindDataToView(data: Any)

    init {
        bViewDataBinding = DataBindingUtil.bind<ViewDataBinding>(LayoutInflater.from(context).inflate(getLayoutId(), null)) as B
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(bViewDataBinding?.root!!)
        onViewCreated()
    }

    abstract fun getLayoutId(): Int

    override fun onViewCreated() {

    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(resId: Int) {
        Toast.makeText(context, context.getString(resId), Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(e: CustomException) {
        Log.d(TAG, "showMessage: " + e.exception)
    }


    override val codeSnippet: CodeSnippet
        get() {
            if (mCodeSnippet == null) {
                mCodeSnippet = CodeSnippet(context)
                return mCodeSnippet!!
            }
            return mCodeSnippet as CodeSnippet
        }


    override val sharedPrefManager: SharedPrefManager
        get() {
            if (mSharedPrefManager == null) {
                mSharedPrefManager = SharedPrefManager(context)
            }
            return mSharedPrefManager as SharedPrefManager
        }



    override fun showSnackBar(message: String) {
        if (bViewDataBinding?.root != null) {
            val snackbar = Snackbar.make(bViewDataBinding?.root!!, message, Snackbar.LENGTH_LONG)
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
        if (bViewDataBinding?.root != null) {
            val snackBar = Snackbar.make(bViewDataBinding?.root!!, "No Network found!", Snackbar.LENGTH_LONG)
            snackBar.setActionTextColor(Color.RED)
            snackBar.setAction(R.string.action_settings) { mCodeSnippet!!.showNetworkSettings() }
            snackBar.show()
        }
    }




}