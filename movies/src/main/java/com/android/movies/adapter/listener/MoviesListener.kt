package com.android.movies.adapter.listener

import com.android.baselib.model.dto.response.MovieResults

interface MoviesListener {

    fun onclickItem(position: Int, data: MovieResults)
}