package com.android.baselib.common

import com.android.baselib.common.Constants.Package.Companion.FEATURE_MOVIE_DETAILS

interface Constants {

    interface BundleKeys {
        companion object {
            const val MOVIES_DATA = "moviesData"
        }
    }


    interface ApiHeaderKey {
        companion object {
            const val AUTHORIZATION = "Authorization"
            const val USER_TYPE = "User-Type"
            const val CONTENT_TYPE = "Content-Type"
            const val applicationJson = "application/json"

        }
    }

    interface HttpErrorMessage {
        companion object {
            const val INTERNAL_SERVER_ERROR =
                "Our server is under maintenance. We will resolve shortly!"
            const val FORBIDDEN = "Seems like you haven't permitted to do this operation!"
            const val TIMEOUT = "Unable to connect to server. Please try after sometime"
            const val UNAUTHORIZED = "UnAuthorized"
        }

    }

    interface InternalHttpCode {
        companion object {
            const val FAILURE_CODE = 404
            const val BAD_REQUEST_CODE = 400
            const val UNAUTHORIZED_CODE = 401
            const val INTERNAL_SERVER_ERROR_CODE = 500
            const val TIMEOUT_CODE = 504
        }
    }

    interface PreferenceKey {
        companion object {
            const val TOKEN = "token"
            const val USER_DATA = "userData"
            const val LOGGED_IN = "logged_in"

        }
    }

    interface Activity {
        companion object {
            const val MOVIE_DETAIL = FEATURE_MOVIE_DETAILS + "MovieDetailsActivity"
        }
    }

    interface Package {
        companion object {
            const val APP = "com.android.moviesdb"
            const val FEATURE_MOVIE_DETAILS = "com.android.details."
        }
    }

}