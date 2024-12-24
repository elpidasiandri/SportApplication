package com.example.sportApp.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.sportApp.db.entities.SportEntity
import com.example.sportApp.db.entities.SportEventEntity
import com.example.sportApp.db.entities.SportsWithEvents
import kotlinx.coroutines.flow.Flow

@Dao
interface SportsDao {
    @Transaction
    @Query("SELECT * FROM sports")
    fun getAllSports(): Flow<List<SportsWithEvents>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSports(sports: List<SportEntity>)
    @Query("DELETE FROM sports")
    suspend fun deleteAllSports()
}