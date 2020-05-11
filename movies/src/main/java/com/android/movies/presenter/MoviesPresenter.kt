package com.android.movies.presenter

import android.os.Bundle
import com.android.baselib.common.Constants
import com.android.baselib.model.dto.response.MovieResults
import com.android.baselib.presenter.BasePresenter
import com.android.movies.adapter.MoviesAdapter
import com.android.movies.adapter.listener.MoviesListener
import com.android.movies.model.ModelRepository
import com.android.movies.presenter.ipresenter.IMoviesPresenter
import com.android.movies.view.iview.IMoviesView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoviesPresenter(iMoviesView: IMoviesView) : BasePresenter<IMoviesView>(iMoviesView),
    IMoviesPresenter {

    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(bundle: Bundle?) {
        getMoviesList()
    }

    private fun getMoviesList() {
        iView?.showProgressbar()
        iView?.getActivity()?.let { it ->
            ModelRepository(it, this)
                .getMoviesList()
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    dismissProgressbar()
                    setupAdapter(it.results)
                }, {
                    dismissProgressbar()
                    iView?.showMessage(it)
                })
        }
    }

    private fun setupAdapter(results: MutableList<MovieResults>?) {
        moviesAdapter = MoviesAdapter(results!!, listener)
        iView?.updateAdapter(moviesAdapter)
    }

    private val listener = object : MoviesListener {
        override fun onclickItem(position: Int, data: MovieResults) {
            val bundle = Bundle()
            bundle.putParcelable(Constants.BundleKeys.MOVIES_DATA, data)
            iView?.launchDetails(bundle)
        }
    }
}