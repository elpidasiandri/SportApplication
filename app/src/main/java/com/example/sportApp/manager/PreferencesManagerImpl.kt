package com.example.sportApp.manager

import android.content.Context
import android.content.SharedPreferences
import com.example.sportApp.utils.TimerHelper

class PreferencesManagerImpl(private val context: Context):IPreferencesManager {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    companion object {
        private const val LAST_UPDATED_KEY = "last_updated_timestamp"
    }

    override fun setLastUpdatedTimestamp(timestamp: Long) {
        sharedPreferences.edit().putLong(LAST_UPDATED_KEY, timestamp).apply()
    }

    override fun getLastUpdatedTimestamp(): Long {
        return sharedPreferences.getLong(LAST_UPDATED_KEY, 0L)
    }

    override fun checkIfShouldDoHttpCall(): Boolean {
        val currentTime = System.currentTimeMillis()
        val oneDayInMillis = TimerHelper.oneDay
        return getLastUpdatedTimestamp() == 0L || (currentTime - getLastUpdatedTimestamp() > oneDayInMillis)
    }
}