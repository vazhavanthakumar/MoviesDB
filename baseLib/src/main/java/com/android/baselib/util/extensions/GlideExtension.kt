package com.android.baselib.util.extensions

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.baselib.R
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

object GlideExtension {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, url: String) {
        view.loadUrl(url)
    }

    @BindingAdapter("circleImageUrl")
    @JvmStatic
    fun loadCircleImage(view: ImageView, url: String) {
        view.loadCircleImage(url)
    }
}

fun ImageView.loadBitmapCircleImage(bitmap: Bitmap?) {
    if (bitmap != null) {
        Glide.with(context).load(bitmap)
            .transition(GenericTransitionOptions.with(R.anim.zoom_in_center))
            .apply(RequestOptions.circleCropTransform().error(R.drawable.ic_place_holder))
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: com.bumptech.glide.load.DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            })
            .into(this)
    } else {
        Glide.with(context).load(R.drawable.ic_place_holder)
            .transition(GenericTransitionOptions.with(R.anim.zoom_in_center))
            .apply(RequestOptions.circleCropTransform())
            .into(this)
    }
}

fun ImageView.loadUrl(url: String?) {
    val imagePath = context.getString(R.string.tmdb_image_url) + url
    Glide.with(context).load(imagePath)
        .transition(GenericTransitionOptions.with(R.anim.zoom_in_center))
        .apply(
            RequestOptions().placeholder(
                ContextCompat.getDrawable(
                    context, R.drawable.ic_place_holder
                )
            )
        )
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: com.bumptech.glide.load.DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

        })
        .into(this)
}


fun ImageView.loadCircleImage(url: String?) {
    if (!TextUtils.isEmpty(url)) {
        Glide.with(context).load(context.getString(R.string.tmdb_image_url) + url)
            .transition(GenericTransitionOptions.with(R.anim.zoom_in_center))
            .apply(RequestOptions.circleCropTransform().error(R.drawable.ic_place_holder))
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: com.bumptech.glide.load.DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            })
            .into(this)
    } else {
        Glide.with(context).load(R.drawable.ic_place_holder)
            .transition(GenericTransitionOptions.with(R.anim.zoom_in_center))
            .apply(RequestOptions.circleCropTransform())
            .into(this)
    }
}