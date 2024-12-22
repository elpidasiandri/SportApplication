package com.example.sportapp.useCases.db

import com.example.sportapp.repositories.database.ISportDatabaseRepo

class GetLocallySportsAndEventsUseCase(private val repo: ISportDatabaseRepo){
    suspend operator fun invoke() = repo.getLocallySportsAndEvents()
}
