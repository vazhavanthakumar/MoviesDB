package com.android.moviesdb.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.navigation.findNavController
import com.android.baselib.view.activity.BaseActivity
import com.android.moviesdb.R
import com.android.moviesdb.databinding.ActivityHomeBinding
import com.android.moviesdb.presenter.HomePresenter
import com.android.moviesdb.presenter.ipresenter.IHomePresenter
import com.android.moviesdb.view.iview.IHomeView

class HomeActivity : BaseActivity<ActivityHomeBinding, IHomePresenter>(),
    IHomeView {

    private var currentFragment = R.id.navMovies

    override fun getLayoutId(): Int = R.layout.activity_home

    override fun onInitializePresenter(): IHomePresenter = HomePresenter(this)

    override fun onPresenterCreated() {
        bViewDataBinding?.bottomNavigationHome?.setOnNavigationItemSelectedListener {
            currentFragment = it.itemId
            performNavigation(currentFragment)
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun onBackPressed() {
        if (currentFragment == R.id.navMovies) {
            doubleTapExit()
        } else {
            bViewDataBinding?.bottomNavigationHome?.selectedItemId =
                R.id.navMovies
            performNavigation(R.id.navMovies)
        }
    }

    private fun performNavigation(flag: Int) {
        val controller = findNavController(R.id.homeContainer)
        controller.popBackStack()
        when (flag) {
            R.id.navMovies -> controller.navigate(
                R.id.moviesFragment
            )
            R.id.navStars -> controller.navigate(
                R.id.starsFragment
            )
        }
    }
}
