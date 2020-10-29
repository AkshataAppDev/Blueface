package com.example.bluefacecodingchallenge.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.bluefacecodingchallenge.database.CityDao
import com.example.bluefacecodingchallenge.database.CityDatabase
import com.example.bluefacecodingchallenge.database.CityDatabase.Companion.DATABASE_NAME
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideAppDb(app: Application): CityDatabase {
        return Room
            .databaseBuilder(app, CityDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration() // get correct db version if scheme changed
            .build()
    }

    @Singleton
    @Provides
    fun provideCityDao(db: CityDatabase): CityDao {
        return db.cityDatabaseDao
    }


    @Singleton
    @Provides
    fun provideLastLocation(context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }
}