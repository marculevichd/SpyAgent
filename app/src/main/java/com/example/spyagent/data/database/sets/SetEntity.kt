package com.example.spyagent.data.database.sets

import androidx.room.*

@Entity(tableName = "sets_table")
@TypeConverters(Converters::class)
data class SetEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("setName")
    val setName: String,
    @ColumnInfo("listWords")
    val listWords: ArrayList<String>
)

