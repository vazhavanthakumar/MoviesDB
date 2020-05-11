package com.android.baselib.model

import android.content.Context
import com.android.baselib.common.Constants
import com.android.baselib.model.dto.response.BaseResponse
import com.android.baselib.model.webservice.ApiClient
import com.android.baselib.presenter.ipresenter.IRepositoryModel
import com.android.baselib.util.CustomException
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

open class BaseModelRepository(var context: Context, var iRepositoryModel: IRepositoryModel) {

    var TAG = javaClass.simpleName

    var apiClient = ApiClient(context)

    open fun <T : BaseResponse> enqueue(callback: Call<T>): Observable<T> {

        if (iRepositoryModel.isInternetConnected()) {
            return Observable.create<T> {
                callback.enqueue(object : Callback<T> {
                    override fun onResponse(call: Call<T>, response: Response<T>) {

                        if (response.body() != null && response.isSuccessful) {
                            val result = response.body()
                            if (response.code() == 200) {
                                dismissProcess()
                                result?.let { it1 -> it.onNext(it1) }
                            } else {
                                dismissProcess()
                                it.onError(CustomException(response.code(), result))
                            }
                        } else if (response.body() != null) {
                            dismissProcess()
                            it.onError(CustomException(response.code(), response.body()))
                        } else if (response.code() == Constants.InternalHttpCode.UNAUTHORIZED_CODE) {
                            dismissProcess()
                            iRepositoryModel.logoutUser()
                        } else {
                            dismissProcess()

                            if (response.errorBody() != null) {
                                it.onError(
                                    CustomException(
                                        response.code(),
                                        response.errorBody()?.string()!!
                                    )
                                )
                            } else
                                it.onError(
                                    CustomException(
                                        response.code(),
                                        Constants.HttpErrorMessage.INTERNAL_SERVER_ERROR
                                    )
                                )
                        }
                    }

                    override fun onFailure(call: Call<T>?, t: Throwable?) {
                        try {
                            dismissProcess()
                            Timber.d( "onFailure : %s", t)
                            t?.let { it1 -> it.onError(CustomException(501, t.localizedMessage)) }
                        } catch (e: Exception) {
                            dismissProcess()
                        }

                    }

                })
            }
        } else {
            iRepositoryModel.showNetworkUnavailable()
            dismissProcess()
            return Observable.never()
        }
    }

    private fun dismissProcess() {
        iRepositoryModel.dismissProgressbar()
    }


}