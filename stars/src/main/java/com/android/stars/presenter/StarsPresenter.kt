package com.android.stars.presenter

import android.os.Bundle
import com.android.baselib.presenter.BasePresenter
import com.android.stars.adapter.StarsAdapter
import com.android.stars.adapter.listener.StarsListener
import com.android.stars.model.ModelRepository
import com.android.stars.model.response.Results
import com.android.stars.presenter.ipresenter.IStarsPresenter
import com.android.stars.view.iview.IStarsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class StarsPresenter(iStarsView: IStarsView) : BasePresenter<IStarsView>(iStarsView),
    IStarsPresenter {

    private lateinit var starAdapter: StarsAdapter

    override fun onCreate(bundle: Bundle?) {
        getStarsList()
    }

    private fun getStarsList() {
        iView?.showProgressbar()
        iView?.getActivity()?.let { it ->
            ModelRepository(it, this)
                .getStarsList()
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    dismissProgressbar()
                    setUpAdapter(it.results)
                }, {
                    dismissProgressbar()
                    iView?.showMessage(it)
                })
        }
    }

    private fun setUpAdapter(results: MutableList<Results>) {
        starAdapter = StarsAdapter(results, listener)
        iView?.updateAdapter(starAdapter)
    }

    private val listener = object : StarsListener {
        override fun onClickItem(position: Int, data: Results) {
        }
    }
}