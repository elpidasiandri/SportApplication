package com.example.sportApp.useCases.db

import com.example.sportApp.db.entities.SportEntity
import com.example.sportApp.db.entities.SportEventEntity
import com.example.sportApp.repositories.database.ISportDatabaseRepo

class InsertSportsWithEventsUseCase(private val repo: ISportDatabaseRepo) {
    suspend operator fun invoke(
        sports: List<SportEntity>,
        events: List<SportEventEntity>,
    ) = repo.setSportsAndEvents(
        sports = sports,
        events = events
    )
}