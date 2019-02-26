package io.andronicus.forecastmvvm.data.provider

import android.content.Context
import androidx.preference.PreferenceManager

/**
 * Created by Andronicus on 2/26/2019.
 */
abstract class PreferenceProvider(context: Context) {
    private val appContext = context.applicationContext
    protected val preferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)
}