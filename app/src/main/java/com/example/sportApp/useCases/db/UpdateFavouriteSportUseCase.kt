package com.example.sportApp.useCases.db

import com.example.sportApp.repositories.database.ISportDatabaseRepo

class UpdateFavouriteSportUseCase(private val repo: ISportDatabaseRepo) {
    suspend operator fun invoke(isMyFavourite: Boolean, eventId: String) = repo.isMyFavourite(
        isMyFavourite = isMyFavourite,
        eventId = eventId
    )

}