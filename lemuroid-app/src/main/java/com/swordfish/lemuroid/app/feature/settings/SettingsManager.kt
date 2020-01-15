package com.swordfish.lemuroid.app.feature.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.swordfish.lemuroid.R
import com.swordfish.lemuroid.common.kotlin.boolDelegate

class SettingsManager(private val context: Context) {

    private var sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    private fun getString(resId: Int) = context.getString(resId)

    var autoSave: Boolean by sharedPreferences.boolDelegate({ getString(R.string.pref_key_autosave) }, true)

    var vibrateOnTouch: Boolean by sharedPreferences.boolDelegate({ getString(R.string.pref_key_vibrate_on_touch) }, true)

    var simulateScreen: Boolean by sharedPreferences.boolDelegate({ getString(R.string.pref_key_shader )}, true)
}