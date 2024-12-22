package com.example.sportapp.repositories.database

import com.example.sportapp.db.daos.SportsDao
import com.example.sportapp.db.daos.SportsWithEventsDao
import com.example.sportapp.db.entities.SportEntity
import com.example.sportapp.db.entities.SportEventEntity
import com.example.sportapp.db.entities.SportsWithEvents

class SportDatabaseRepoImpl(
    private val sportDao: SportsDao,
    private val sportEventsDao: SportsWithEventsDao,
) : ISportDatabaseRepo {
    override suspend fun setSportsAndEvents(
        sports: List<SportEntity>,
        events: List<SportEventEntity>,
    ) {
        sportDao.insertSports(sports)
        sportEventsDao.insertEvents(events)
    }

    override suspend fun getLocallySportsAndEvents(): List<SportsWithEvents> {
        return sportEventsDao.getAllEvents()
    }
}