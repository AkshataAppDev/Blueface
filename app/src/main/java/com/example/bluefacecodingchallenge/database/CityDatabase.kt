package com.example.bluefacecodingchallenge.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CityEntity::class], version = 1, exportSchema = false)
abstract class CityDatabase : RoomDatabase() {
    abstract val cityDatabaseDao: CityDao

    companion object {
        @Volatile
        private var INSTANCE: CityDatabase? = null
        fun getInstance(context: Context): CityDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CityDatabase::class.java,
                        DATABASE_NAME

                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

        const val DATABASE_NAME: String = "city_database"
    }
}
