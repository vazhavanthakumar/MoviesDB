package com.android.baselib.model.dto.response


open class BaseResponse {
    var errorYN: String? = null
    var status: Int? = null
    var message: String? = null
    var description: String? = null
    var pageNo: Int? = null
    var totalPages: Int? = null
    var total: Int? = null
    var totalCount: Int? = null
}