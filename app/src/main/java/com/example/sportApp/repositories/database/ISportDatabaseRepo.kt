package com.example.sportapp.repositories.database

import com.example.sportapp.db.entities.SportEntity
import com.example.sportapp.db.entities.SportEventEntity
import com.example.sportapp.db.entities.SportsWithEvents

interface ISportDatabaseRepo {
    suspend fun setSportsAndEvents(
        sports: List<SportEntity>,
        events: List<SportEventEntity>,
    )

    suspend fun getLocallySportsAndEvents(): List<SportsWithEvents>
}