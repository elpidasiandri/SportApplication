package com.example.sportApp.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sportApp.db.entities.SportEventEntity

@Dao
interface SportsEventsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(events: List<SportEventEntity>)

    @Query("DELETE FROM sport_events")
    suspend fun deleteAllSportEvents()
    @Query("UPDATE sport_events SET isFavourite = :flag WHERE eventId = :eventId")
    suspend fun updateIsFavourite(eventId: String, flag: Boolean)
}