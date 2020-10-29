package com.example.bluefacecodingchallenge.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CityDao {
    @Insert
    fun insert(city: CityEntity)

    @Query("SELECT * from city_table where cityname LIKE '%' || :key || '%' ")
    fun getCity(key: String): List<CityEntity>

    @Query("SELECT * from city_table ORDER BY addeddate DESC")
     fun getAllCities(): List<CityEntity>

    @Query("SELECT * from city_table where cityname LIKE :key ")
    fun getCityForQuery(key: String): CityEntity
}