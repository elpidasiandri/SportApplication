package com.example.sportApp.preferences

interface IPreferencesManager {
    fun setLastUpdatedTimestamp(timestamp: Long)
    fun getLastUpdatedTimestamp(): Long
    fun checkIfShouldDoHttpCall(): Boolean
}