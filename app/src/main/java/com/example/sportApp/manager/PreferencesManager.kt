package com.example.sportapp.manager

import android.content.Context
import android.content.SharedPreferences
import com.example.sportapp.utils.TimerHelper
import java.lang.ref.WeakReference

class PreferencesManager(weakContext: WeakReference<Context>) {

    private val sharedPreferences: SharedPreferences? = weakContext.get()?.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    companion object {
        private const val LAST_UPDATED_KEY = "last_updated_timestamp"
    }

    fun setLastUpdatedTimestamp(timestamp: Long) {
        sharedPreferences?.edit()?.putLong(LAST_UPDATED_KEY, timestamp)?.apply()
    }

    fun getLastUpdatedTimestamp(): Long {
        return sharedPreferences?.getLong(LAST_UPDATED_KEY, 0L)?:0L
    }
    fun checkIfShouldDoHttpCall():Boolean {
        val currentTime = System.currentTimeMillis()
        val oneDayInMillis = TimerHelper.oneDay
        return getLastUpdatedTimestamp() == 0L || (currentTime -getLastUpdatedTimestamp() > oneDayInMillis)
    }
}