package com.example.bluefacecodingchallenge.parser

import com.example.bluefacecodingchallenge.model.CityData

sealed class ParserResponse {
    data class Success(val cityData: CityData) : ParserResponse()
    data class Error(val message: String, val type:ErrorType) :ParserResponse()
}

enum class ErrorType{
    NullOrEmpty,
    NotValid,
}