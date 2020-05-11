package com.android.movies.model.webservice

import com.android.baselib.model.dto.response.BaseResponse
import com.android.movies.model.response.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiInterface {

    @GET("3/discover/movie?api_key=3fa9058382669f72dcb18fb405b7a831&language=en&sort_by=popularity.desc")
    fun getMovies(@Query("page") page: Int): Call<MoviesResponse>
}