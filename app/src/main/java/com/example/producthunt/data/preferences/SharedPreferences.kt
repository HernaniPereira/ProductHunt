package com.example.producthunt.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

private const val KEY_SAVED_AT = "key_saved_at"

class SharedPreferences (
    context: Context
){
    private val appContext = context.applicationContext


    private val preference : SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun savelastSavedAt(savedAt: String){
        preference.edit().putString(
            KEY_SAVED_AT,
            savedAt
        ).apply()
    }

    fun getlastSavedAt():String?{
        return preference.getString(KEY_SAVED_AT,null)
    }
}