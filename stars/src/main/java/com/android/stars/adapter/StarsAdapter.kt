package com.android.stars.adapter

import android.view.ViewGroup
import com.android.baselib.adapter.BaseRecyclerAdapter
import com.android.stars.R
import com.android.stars.adapter.listener.StarsListener
import com.android.stars.adapter.viewholder.StarsViewHolder
import com.android.stars.databinding.InflateStarsItemBinding
import com.android.stars.model.response.Results

class StarsAdapter(data: MutableList<Results>, var listener: StarsListener) :
    BaseRecyclerAdapter<Results, StarsViewHolder>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarsViewHolder =
        StarsViewHolder(
            inflateDataBinding(
                R.layout.inflate_stars_item,
                parent
            ) as InflateStarsItemBinding, listener
        )


}