package com.android.stars.model

import android.content.Context
import com.android.baselib.model.BaseModelRepository
import com.android.baselib.presenter.ipresenter.IRepositoryModel
import com.android.stars.model.webservice.ApiInterface

class ModelRepository(context: Context, iRepositoryModel: IRepositoryModel) :
    BaseModelRepository(context, iRepositoryModel) {

    private fun getApiInterface() = apiClient.getClient().create(ApiInterface::class.java)

    fun getStarsList() = enqueue(getApiInterface().getStars(1))
}