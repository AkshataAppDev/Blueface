package com.example.bluefacecodingchallenge.di.main;


import com.example.bluefacecodingchallenge.database.CityDao
import com.example.bluefacecodingchallenge.dispatcher.AppDispatchers
import com.example.bluefacecodingchallenge.dispatcher.AppDispatchersImpl
import com.example.bluefacecodingchallenge.repositories.CityRepo
import com.example.bluefacecodingchallenge.repositories.CityRepoImpl
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideAppDispatchers(): AppDispatchers {
        return AppDispatchersImpl()
    }

    @MainScope
    @Provides
    fun provideCityDatabaseRepo(
        cityDao: CityDao,
        appDispatchers: AppDispatchers
    ): CityRepo {
        return CityRepoImpl(cityDao, appDispatchers)
    }

}