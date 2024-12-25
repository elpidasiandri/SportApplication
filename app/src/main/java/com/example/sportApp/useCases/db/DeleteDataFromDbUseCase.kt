package com.example.sportApp.useCases.db

import com.example.sportApp.repositories.database.ISportDatabaseRepo

class DeleteDataFromDbUseCase(private val repo: ISportDatabaseRepo) {
    suspend operator fun invoke()  = repo.deleteAll()
}