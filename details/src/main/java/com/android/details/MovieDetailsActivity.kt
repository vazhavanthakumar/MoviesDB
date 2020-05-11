package com.android.details

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.baselib.common.Constants
import com.android.baselib.model.dto.response.MovieResults
import com.google.gson.Gson

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val bundle = intent.extras
        val results: MovieResults = bundle?.getParcelable<MovieResults>(Constants.BundleKeys.MOVIES_DATA)!!
        Log.e("TAG", "results : ${Gson().toJson(results)}")

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
