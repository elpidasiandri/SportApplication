package com.example.sportApp.mock

import com.example.sportApp.manager.IPreferencesManager

class PreferenceManagerMock: IPreferencesManager {
    override fun setLastUpdatedTimestamp(timestamp: Long) {
    }

    override fun getLastUpdatedTimestamp(): Long {
     return 0L
    }

    override fun checkIfShouldDoHttpCall(): Boolean {
        return true
    }
}