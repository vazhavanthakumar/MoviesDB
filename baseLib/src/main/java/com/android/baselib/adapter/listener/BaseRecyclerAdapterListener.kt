package com.android.baselib.adapter.listener


interface BaseRecyclerAdapterListener<T> {

    fun onClickItem(position: Int, data: T?)

}