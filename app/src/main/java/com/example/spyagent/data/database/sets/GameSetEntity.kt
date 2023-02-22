package com.example.spyagent.data.database.sets

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "game_sets_table")
@TypeConverters(Converters::class)
data class GameSetEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("setName")
    val setName: String,
    @ColumnInfo("listWords")
    val listWords: ArrayList<String>
    )