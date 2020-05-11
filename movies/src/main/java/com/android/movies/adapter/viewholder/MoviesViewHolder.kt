package com.android.movies.adapter.viewholder

import com.android.baselib.adapter.viewholder.BaseViewHolder
import com.android.baselib.model.dto.response.MovieResults
import com.android.baselib.util.extensions.GlideExtension.loadImage
import com.android.movies.adapter.listener.MoviesListener
import com.android.movies.databinding.InflateMoviesItemBinding

class MoviesViewHolder(binding: InflateMoviesItemBinding, var listener: MoviesListener) :
    BaseViewHolder<MovieResults, InflateMoviesItemBinding>(binding) {

    override fun populateData(data: MovieResults) {
        viewDataBinding.data = data
        viewDataBinding.listener = listener
        viewDataBinding.position = adapterPosition
        loadImage(viewDataBinding.moviesImg,data.poster_path)
    }
}