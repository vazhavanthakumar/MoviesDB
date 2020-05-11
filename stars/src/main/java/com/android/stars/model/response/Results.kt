package com.android.stars.model.response

import com.google.gson.annotations.SerializedName

data class Results (

    @SerializedName("popularity") val popularity : Double,
    @SerializedName("known_for_department") val known_for_department : String,
    @SerializedName("name") val name : String,
    @SerializedName("id") val id : Int,
    @SerializedName("profile_path") val profile_path : String,
    @SerializedName("adult") val adult : Boolean,
    @SerializedName("known_for") val known_for : MutableList<KnownFor>,
    @SerializedName("gender") val gender : Int
)