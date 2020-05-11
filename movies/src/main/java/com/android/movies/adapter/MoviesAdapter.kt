package com.android.movies.adapter

import android.view.ViewGroup
import com.android.baselib.adapter.BaseRecyclerAdapter
import com.android.baselib.model.dto.response.MovieResults
import com.android.movies.R
import com.android.movies.adapter.listener.MoviesListener
import com.android.movies.adapter.viewholder.MoviesViewHolder
import com.android.movies.databinding.InflateMoviesItemBinding

class MoviesAdapter(data: MutableList<MovieResults>, var listener: MoviesListener) :
    BaseRecyclerAdapter<MovieResults, MoviesViewHolder>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder =
        MoviesViewHolder(
            inflateDataBinding(
                R.layout.inflate_movies_item,
                parent
            ) as InflateMoviesItemBinding, listener
        )


}