package com.android.baselib.presenter.ipresenter

import android.content.Intent
import android.os.Bundle


interface IPresenter : IRepositoryModel {

    fun onCreate(bundle: Bundle?)

    fun onStart()

    fun onResume()

    fun onPause()

    fun onStop()

    fun onDestroy()

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)


}