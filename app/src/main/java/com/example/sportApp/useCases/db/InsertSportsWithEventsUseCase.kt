package com.example.sportapp.useCases.db

import com.example.sportapp.db.entities.SportEntity
import com.example.sportapp.db.entities.SportEventEntity
import com.example.sportapp.repositories.database.ISportDatabaseRepo

class InsertSportsWithEventsUseCase(private val repo: ISportDatabaseRepo) {
    suspend operator fun invoke(
        sports: List<SportEntity>,
        events: List<SportEventEntity>,
    ) = repo.setSportsAndEvents(
        sports = sports,
        events = events
    )
}