package com.android.baselib.view.widgets.recylerview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.android.baselib.view.widgets.recylerview.CustomRecyclerView
import com.baselib.R
import com.baselib.databinding.InflateEmptyViewBinding


class EmptyViewContainer : LinearLayout {


    private lateinit var view: InflateEmptyViewBinding
    private lateinit var rvContainer: CustomRecyclerView


    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, arts: AttributeSet) : super(context, arts) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        initView(context)
    }

    private fun initView(context: Context) {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = DataBindingUtil.bind<ViewDataBinding>(
            layoutInflater.inflate(
                R.layout.inflate_empty_view,
                null
            )
        ) as InflateEmptyViewBinding

        rvContainer = CustomRecyclerView(context)
        rvContainer.setEmptyView(view.root)

        addView(
            rvContainer,
            0,
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        )

        addView(
            view.root,
            1,
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        )

    }

    fun getEmptyViewInstance() = view

    fun getRecyclerViewInstance() = rvContainer

    fun removeObserver()
    {
        rvContainer.removeObserver()
    }

}