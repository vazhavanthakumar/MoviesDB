package com.android.movies.view.iview

import android.os.Bundle
import com.android.baselib.view.iview.IView
import com.android.movies.adapter.MoviesAdapter

interface IMoviesView : IView {

    fun updateAdapter(adapter: MoviesAdapter)

    fun launchDetails(bundle: Bundle)
}