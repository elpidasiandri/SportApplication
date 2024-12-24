package com.example.sportApp.repositories.database

import com.example.sportApp.db.daos.SportsDao
import com.example.sportApp.db.daos.SportsEventsDao
import com.example.sportApp.db.entities.SportEntity
import com.example.sportApp.db.entities.SportEventEntity
import com.example.sportApp.db.entities.SportsWithEvents
import kotlinx.coroutines.flow.Flow

class SportDatabaseRepoImpl(
    private val sportDao: SportsDao,
    private val sportEventsDao: SportsEventsDao,
) : ISportDatabaseRepo {
    override suspend fun setSportsAndEvents(
        sports: List<SportEntity>,
        events: List<SportEventEntity>,
    ) {
        if (sports.isNotEmpty()) deleteAll()
        sportDao.insertSports(sports)
        sportEventsDao.insertEvents(events)
    }

    override suspend fun getLocallySportsAndEvents(): Flow<List<SportsWithEvents>> {
        return sportDao.getAllSports()
    }

    override suspend fun deleteAll() {
        sportDao.deleteAllSports()
        sportEventsDao.deleteAllSportEvents()
    }

    override suspend fun isMyFavourite(isMyFavourite: Boolean, eventId: String) {
        return sportEventsDao.updateIsFavourite(
            flag = isMyFavourite,
            eventId = eventId
        )
    }
}