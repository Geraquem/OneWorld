package com.mmfsin.oneworld.data.ddbb

import android.content.SharedPreferences
import androidx.core.content.edit
import com.mmfsin.oneworld.utils.SP_LATEST_EVENTS_CATEGORY
import com.mmfsin.oneworld.utils.SP_USER_EVENTS_SERVER
import javax.inject.Inject

class SharedPrefs @Inject constructor(
    private val prefs: SharedPreferences
) {

    fun updateLatestEventsCategory(value: Int) {
        prefs.edit { putInt(SP_LATEST_EVENTS_CATEGORY, value) }
    }

    fun getLatestEventsCategory(): Int {
        return prefs.getInt(SP_LATEST_EVENTS_CATEGORY, 0)
    }

    /************************************************************/

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