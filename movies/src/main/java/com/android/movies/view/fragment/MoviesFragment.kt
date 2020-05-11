package com.android.movies.view.fragment

import android.content.Intent
import android.os.Bundle
import com.android.baselib.common.Constants
import com.android.baselib.view.fragment.BaseFragment
import com.android.movies.R
import com.android.movies.adapter.MoviesAdapter
import com.android.movies.databinding.FragmentMoviesBinding
import com.android.movies.presenter.MoviesPresenter
import com.android.movies.view.iview.IMoviesView

class MoviesFragment : BaseFragment<FragmentMoviesBinding, MoviesPresenter>(), IMoviesView {

    override fun getLayoutId(): Int = R.layout.fragment_movies

    override fun onInitializePresenter(): MoviesPresenter = MoviesPresenter(this)

    override fun onPresenterCreated() {

    }

    override fun updateAdapter(adapter: MoviesAdapter) {
        viewDataBinding?.adapter = adapter
    }

    override fun launchDetails(bundle: Bundle) {
       navigateTo(bundle,Constants.Activity.MOVIE_DETAIL,null)
    }



}
