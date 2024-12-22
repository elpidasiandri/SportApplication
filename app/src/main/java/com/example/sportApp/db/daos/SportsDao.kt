package com.example.sportapp.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sportapp.db.entities.SportEntity
import com.example.sportapp.db.entities.SportEventEntity

@Dao
interface SportsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSports(sports: List<SportEntity>)

    @Query("SELECT * FROM sports")
    suspend fun getAllSports(): List<SportEntity>
}