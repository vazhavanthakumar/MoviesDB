package com.android.stars.model.webservice

import com.android.stars.model.response.TvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("3/person/popular?api_key=3fa9058382669f72dcb18fb405b7a831&language=en")
    fun getStars(@Query("page") page: Int): Call<TvResponse>
}