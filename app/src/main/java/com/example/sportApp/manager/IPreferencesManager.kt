package com.example.sportApp.manager

interface IPreferencesManager {
    fun setLastUpdatedTimestamp(timestamp: Long)
    fun getLastUpdatedTimestamp(): Long
    fun checkIfShouldDoHttpCall(): Boolean
}