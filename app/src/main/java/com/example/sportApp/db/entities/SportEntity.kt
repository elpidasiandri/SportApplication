package com.example.sportApp.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sports")
data class SportEntity(
    @PrimaryKey val sportId: String,
    val sportName: String
)
