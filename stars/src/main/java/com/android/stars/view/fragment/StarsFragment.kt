package com.android.stars.view.fragment

import com.android.baselib.view.fragment.BaseFragment
import com.android.stars.R
import com.android.stars.adapter.StarsAdapter
import com.android.stars.databinding.FragmentStarsBinding
import com.android.stars.presenter.StarsPresenter
import com.android.stars.presenter.ipresenter.IStarsPresenter
import com.android.stars.view.iview.IStarsView

class StarsFragment : BaseFragment<FragmentStarsBinding, IStarsPresenter>(), IStarsView {

    override fun getLayoutId(): Int = R.layout.fragment_stars

    override fun onInitializePresenter(): IStarsPresenter = StarsPresenter(this)

    override fun updateAdapter(adapter: StarsAdapter) {
        viewDataBinding?.adapter = adapter
    }
}
