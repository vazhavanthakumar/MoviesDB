package com.android.stars.adapter.viewholder

import com.android.baselib.adapter.viewholder.BaseViewHolder
import com.android.baselib.util.extensions.GlideExtension.loadCircleImage
import com.android.stars.adapter.listener.StarsListener
import com.android.stars.databinding.InflateStarsItemBinding
import com.android.stars.model.response.Results

class StarsViewHolder(binding: InflateStarsItemBinding, var listener: StarsListener) :
    BaseViewHolder<Results, InflateStarsItemBinding>(binding) {

    override fun populateData(data: Results) {
        viewDataBinding.data = data
        viewDataBinding.listener = listener
        viewDataBinding.position = adapterPosition
        loadCircleImage(viewDataBinding.moviesImg, data.profile_path)
    }
}