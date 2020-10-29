package com.example.bluefacecodingchallenge.parser

import com.example.bluefacecodingchallenge.model.City
import com.example.bluefacecodingchallenge.model.CityData
import com.example.bluefacecodingchallenge.model.CityLatLng
import org.json.JSONObject

object DataParser {
    fun parseCityData(data: String): ParserResponse {
        if (data.isEmpty())
            return ParserResponse.Error("Data is either null or empty", ErrorType.NullOrEmpty)

        val jsonObject = JSONObject(data)

        if (!jsonObject.has(CityJsonKeys.city))
            return ParserResponse.Error("Json data is not valid !", ErrorType.NotValid)

        val cityObject = jsonObject.getJSONObject(CityJsonKeys.city)

        if (!validateCityObject(cityObject))
            return ParserResponse.Error("Json data is not valid !", ErrorType.NotValid)

        val locationObject = cityObject.getJSONObject(CityJsonKeys.location)

        if (!validateLocationObject(locationObject))
            return ParserResponse.Error("Json data is not valid !", ErrorType.NotValid)

        val location = CityLatLng(
            locationObject.getDouble(CityJsonKeys.lat),
            locationObject.getDouble(CityJsonKeys.lng)
        )

        val city = City(
            cityObject.getString(CityJsonKeys.name),
            cityObject.getInt(CityJsonKeys.rank),
            location
        )

        val cityData = CityData(city)

        return ParserResponse.Success(cityData)
    }

    private fun validateCityObject(jsonObject: JSONObject): Boolean {
        return jsonObject.has(CityJsonKeys.name) &&
                jsonObject.has(CityJsonKeys.rank) &&
                jsonObject.has(CityJsonKeys.location)
    }

    private fun validateLocationObject(jsonObject: JSONObject): Boolean {
        return jsonObject.has(CityJsonKeys.lat) &&
                jsonObject.has(CityJsonKeys.lng)
    }
}
