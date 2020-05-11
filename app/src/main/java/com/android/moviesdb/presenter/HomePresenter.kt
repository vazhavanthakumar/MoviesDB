package com.android.moviesdb.presenter

import android.os.Bundle
import com.android.baselib.presenter.BasePresenter
import com.android.moviesdb.presenter.ipresenter.IHomePresenter
import com.android.moviesdb.view.iview.IHomeView

class HomePresenter(iHomeView: IHomeView) : BasePresenter<IHomeView>(iHomeView),
    IHomePresenter {

    override fun onCreate(bundle: Bundle?) {

    }
}