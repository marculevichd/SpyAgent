package com.example.spyagent.data.database.sets

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [SetEntity::class, GameSetEntity::class], version = 1, exportSchema = false)
abstract class DataBaseSets : RoomDatabase() {

    abstract fun getSetsDAO(): SetsDAO

    companion object {

        private var DB_SETS_INSTANCE: DataBaseSets? = null


        fun getDataBaseSetsInstance(context: Context): DataBaseSets {
            return DB_SETS_INSTANCE ?: Room
                .databaseBuilder(
                    context.applicationContext,
                    DataBaseSets::class.java,
                    "sets_table"
                ).build()
                .also { DB_SETS_INSTANCE = it }
        }
    }


}