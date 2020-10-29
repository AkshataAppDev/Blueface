package com.example.bluefacecodingchallenge.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "city_table",
    indices = arrayOf(Index(value = ["cityname"], unique = true))
)
 data class CityEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "addeddate")
    var addedDate: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "cityname")
     var cityname: String? = null,

    @ColumnInfo(name = "cityrank")
     var cityrank: Int= -1,

    @ColumnInfo(name = "citylat")
    var citylat: Double= 0.0,

    @ColumnInfo(name = "citylon")
     var citylon: Double = 0.0
)