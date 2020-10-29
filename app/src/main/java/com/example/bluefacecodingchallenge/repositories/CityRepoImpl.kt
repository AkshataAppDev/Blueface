package com.example.bluefacecodingchallenge.repositories

import com.example.bluefacecodingchallenge.database.CityDao
import com.example.bluefacecodingchallenge.database.CityEntity
import com.example.bluefacecodingchallenge.dispatcher.AppDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CityRepoImpl @Inject constructor(
    private val cityDao: CityDao,
    private val appDispatchers: AppDispatchers
) : CityRepo, CoroutineScope {
    override fun saveCity(cityEntity: CityEntity) {
        launch {
            withContext(appDispatchers.io) {
                cityDao.insert(cityEntity)
            }
        }
    }

    override suspend fun getAllCities(): List<CityEntity> {
        return withContext(appDispatchers.io) {
            cityDao.getAllCities()
        }
    }

    override val coroutineContext: CoroutineContext
        get() = appDispatchers.main
}