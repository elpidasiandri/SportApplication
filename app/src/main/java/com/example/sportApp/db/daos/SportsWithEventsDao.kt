package com.example.sportapp.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.sportapp.db.entities.SportEventEntity
import com.example.sportapp.db.entities.SportsWithEvents

@Dao
interface SportsWithEventsDao {
    @Transaction
    @Query("SELECT * FROM sports")
    suspend fun getAllEvents(): List<SportsWithEvents>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(events: List<SportEventEntity>)
}