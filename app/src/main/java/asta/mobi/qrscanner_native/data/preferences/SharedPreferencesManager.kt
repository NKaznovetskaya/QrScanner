package asta.mobi.qrscanner_native.data.preferences

import android.content.SharedPreferences

class SharedPreferencesManager(private val sharedPreferences: SharedPreferences) :
    PreferencesManager {

    companion object {
        private const val USER_INFO = "user"
    }
}