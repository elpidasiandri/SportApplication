package com.example.sportapp.db.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "sport_events")
data class SportEventEntity(
    @PrimaryKey(autoGenerate = true) val eventId: Int,
    val sportId: String,
    val eventName: String,
    val eventNameSecond: String,
    val startTime: Int,
    val isFavourite: Boolean
)

data class SportsWithEvents(
    @Embedded val sport: SportEntity,
    @Relation(
        parentColumn = "sportId",
        entityColumn = "sportId"
    )
    val events: List<SportEventEntity>
)