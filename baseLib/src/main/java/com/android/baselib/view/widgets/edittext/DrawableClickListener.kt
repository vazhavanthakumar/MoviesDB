package com.android.baselib.view.widgets.edittext

interface DrawableClickListener {
    enum class DrawablePosition {
        TOP, BOTTOM, LEFT, RIGHT
    };
    fun onClick(target: DrawablePosition)
}