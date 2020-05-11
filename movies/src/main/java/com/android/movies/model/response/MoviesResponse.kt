package com.android.movies.model.response

import com.android.baselib.model.dto.response.MovieResults
import com.android.baselib.model.dto.response.BaseResponse


data class MoviesResponse(
    var page: Int? = 0,
    var total_results: Int? = 0,
    var total_pages: Int? = 0,
    var results: MutableList<MovieResults>? = null
) : BaseResponse()