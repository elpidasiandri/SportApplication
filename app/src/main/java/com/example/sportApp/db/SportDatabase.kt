package com.example.sportApp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sportApp.db.daos.SportsDao
import com.example.sportApp.db.daos.SportsEventsDao
import com.example.sportApp.db.entities.SportEntity
import com.example.sportApp.db.entities.SportEventEntity

@Database(
    entities = [SportEntity::class, SportEventEntity::class],
    version = 1
)
abstract class SportDatabase : RoomDatabase() {
    abstract fun sportsDao(): SportsDao
    abstract fun sportsWithEventsDao(): SportsEventsDao
}