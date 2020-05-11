package com.android.movies.model

import android.content.Context
import com.android.baselib.common.Constants
import com.android.baselib.model.BaseModelRepository
import com.android.baselib.presenter.ipresenter.IRepositoryModel
import com.android.movies.model.response.MoviesResponse
import com.android.movies.model.webservice.ApiInterface
import io.reactivex.Observable

open class ModelRepository(context: Context, iRepositoryModel: IRepositoryModel) :
    BaseModelRepository(context, iRepositoryModel) {

    private fun getApiInterface(): ApiInterface =
        apiClient.getClient().create(ApiInterface::class.java)

    fun getMoviesList(): Observable<MoviesResponse> =
        enqueue(getApiInterface().getMovies(1))

}