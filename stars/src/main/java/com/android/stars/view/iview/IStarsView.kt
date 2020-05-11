package com.android.stars.view.iview

import com.android.baselib.view.iview.IView
import com.android.stars.adapter.StarsAdapter

interface IStarsView : IView {

    fun updateAdapter(adapter: StarsAdapter)
}