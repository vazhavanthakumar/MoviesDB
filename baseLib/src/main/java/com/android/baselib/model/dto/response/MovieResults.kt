package com.android.baselib.model.dto.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResults(
    var popularity: Double,
    var vote_count: Int,
    var video: Boolean,
    var poster_path: String,
    var id: Int,
    var adult: Boolean,
    var backdrop_path: String,
    var original_language: String,
    var original_title: String,
    var genre_ids: MutableList<Int>,
    var title: String,
    var overview: String,
    var release_date: String
) : Parcelable