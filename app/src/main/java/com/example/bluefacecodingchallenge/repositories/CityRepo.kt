package com.example.bluefacecodingchallenge.repositories

import com.example.bluefacecodingchallenge.database.CityEntity

interface CityRepo {
    fun saveCity(cityEntity: CityEntity)

    suspend fun getAllCities(): List<CityEntity>
}