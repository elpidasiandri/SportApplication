package com.example.sportapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sportapp.db.daos.SportsDao
import com.example.sportapp.db.daos.SportsWithEventsDao
import com.example.sportapp.db.entities.SportEntity
import com.example.sportapp.db.entities.SportEventEntity

@Database(
    entities = [SportEntity::class, SportEventEntity::class],
    version = 1
)
abstract class SportDatabase : RoomDatabase() {
    abstract fun getSportsDao(): SportsDao
    abstract fun getSportsWithEventsDao(): SportsWithEventsDao
}