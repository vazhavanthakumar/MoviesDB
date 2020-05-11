package com.android.baselib.util.sharepreference

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


class SharedPref(private var context: Context) {

    /**
     * Singleton object for the shared preference.
     *
     * @param context Context of current state of the application/object
     * @return SharedPreferences object is returned.
     */

    private var preference: SharedPreferences? = null

    private fun getPreferenceInstance(): SharedPreferences? {
        return if (preference != null) {
            preference
        } else {
            //TODO: Shared Preference name has to be set....
            //preference = SecurePreferences(context, BuildConfig.SECURED_PREFERNCES_PASSWORD, BuildConfig.SECURED_PREFERNCES_FILENAME)
            preference = context.applicationContext.getSharedPreferences("Star ATOM", MODE_PRIVATE)
            preference
        }
    }

    /**
     * Set the String value in the shared preference W.R.T the given key.
     *
     * @param context Context of current state of the application/object
     * @param key     String used as a key for accessing the value.
     * @param value   String value which is to be stored in shared preference.
     */

    fun setSharedValue( key: String, value: String?) {
        getPreferenceInstance()
        val editor = preference!!.edit()
        editor.putString(key, value)
        editor.apply()
    }

    /**
     * Set the Integer value in the shared preference W.R.T the given key.
     *
     * @param context Context of current state of the application/object
     * @param key     String used as a key for accessing the value.
     * @param value   Integer value which is to be stored in shared preference.
     */

    fun setSharedValue(key: String, value: Int) {
        getPreferenceInstance()
        val editor = preference!!.edit()
        editor.putInt(key, value)
        editor.commit()
    }

    fun clearSharedValue() {
        getPreferenceInstance()
        val editor = preference!!.edit()
        editor.clear()
        editor.commit()
    }

    /**
     * Set the boolean value in the shared preference W.R.T the given key.
     *
     * @param context Context of current state of the application/object
     * @param key     String used as a key for accessing the value.
     * @param value   Boolean value which is to be stored in shared preference.
     */

    fun setSharedValue(key: String, value: Boolean?) {
        getPreferenceInstance()
        val editor = preference!!.edit()
        editor.putBoolean(key, value!!)
        editor.apply()
    }

    /**
     * Returns Boolean value for the given key.
     * By default it will return "false".
     *
     * @param context Context of current state of the application/object
     * @param key     String used as a key for accessing the value.
     * @return false by default; returns the Boolean value for the given key.
     */

    fun getBooleanValue(key: String): Boolean? {
        getPreferenceInstance()
        return preference?.getBoolean(key, false)
    }

    /**
     * Returns Integer value for the given key.
     * By default it will return "-1".
     *
     * @param context Context of current state of the application/object
     * @param key     String used as a key for accessing the value.
     * @return -1 by default; returns the Integer value for the given key.
     */

    fun getIntValue(key: String): Int {
        getPreferenceInstance()
        return preference?.getInt(key, -1)!!
    }


    /**
     * Returns String value for the given key.
     * By default it will return null.
     *
     * @param context Context of current state of the application/object
     * @param key     String used as a key for accessing the value.
     * @return null by default; returns the String value for the given key.
     */

    fun getStringValue(key: String): String? {
        getPreferenceInstance()
        return preference?.getString(key, null)
    }

    fun getStringDefaultValue(key: String): String {
        getPreferenceInstance()
        return preference?.getString(key, "")!!
    }

    fun setSharedIntData(key: String, value: Int) {
        getPreferenceInstance()
        val editor = preference!!.edit()
        editor.putInt(key, value)
        editor.commit()
    }


    fun setSharedStringData(key: String, value: String) {
        getPreferenceInstance()
        val editor = preference!!.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun getSharedStringData(key: String): String? {
        getPreferenceInstance()
        return preference!!.getString(key, null)
    }

    fun getSharedIntData(key: String): Int {
        getPreferenceInstance()
        return preference!!.getInt(key, 0)
    }

}