package com.android.stars.adapter.listener

import com.android.stars.model.response.Results

interface StarsListener {

    fun onClickItem(position: Int, data: Results)
}