package com.example.sportApp.useCases.db

import com.example.sportApp.db.entities.SportsWithEvents
import com.example.sportApp.repositories.database.ISportDatabaseRepo
import kotlinx.coroutines.flow.Flow

class GetLocallySportsAndEventsUseCase(private val repo: ISportDatabaseRepo){
    suspend operator fun invoke() : Flow<List<SportsWithEvents>> = repo.getLocallySportsAndEvents()
}
