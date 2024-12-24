package com.example.sportApp.repositories.database

import com.example.sportApp.db.entities.SportEntity
import com.example.sportApp.db.entities.SportEventEntity
import com.example.sportApp.db.entities.SportsWithEvents
import kotlinx.coroutines.flow.Flow

interface ISportDatabaseRepo {
    suspend fun setSportsAndEvents(
        sports: List<SportEntity>,
        events: List<SportEventEntity>,
    )

    suspend fun getLocallySportsAndEvents(): Flow<List<SportsWithEvents>>
    suspend fun deleteAll()
    suspend fun isMyFavourite(isMyFavourite:Boolean, eventId:String)
}