package com.mmfsin.oneworld.data.ddbb

import android.content.SharedPreferences
import androidx.core.content.edit
import com.mmfsin.oneworld.utils.SP_USER_EVENTS_SERVER
import javax.inject.Inject

class SharedPrefs @Inject constructor(
    private val prefs: SharedPreferences
) {

    fun searchEventsInServer(value: Boolean) {
        prefs.edit { putBoolean(SP_USER_EVENTS_SERVER, value) }
    }

    fun checkUserEventsFromServer(): Boolean {
        return prefs.getBoolean(SP_USER_EVENTS_SERVER, true)
    }

    fun restartValues() {
        searchEventsInServer(value = true)
    }
}