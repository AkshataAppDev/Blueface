package com.example.bluefacecodingchallenge

import com.example.bluefacecodingchallenge.parser.DataParser
import com.example.bluefacecodingchallenge.parser.ParserResponse
import org.junit.Assert.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CityJsonParserTest {

    companion object {
        @JvmStatic
        fun provideTestJson(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "{\"city\": {\n" +
                            "\"name\": \"Paris\",\n" +
                            "\"rank\": 7,\n" +
                            "}\n" +
                            "}",
                    "{\"city\": {\n" +
                            "\"name\": \"Paris\",\n" +
                            "\"rank\": 7,\n" +
                            "\"location\": {\"lat\": 48.8588376, \"lon\": 2.2768491}\n" +
                            "}\n" +
                            "}"
                ),
                Arguments.of(
                    "{\"city\": {\n" +
                            "\"name\": \"Paris\",\n" +
                            "\"rank\": 7,\n" +
                            "\"location\": {\"lat\": 48.8588376, \"lon\": 2.2768491}\n" +
                            "}\n" +
                            "}",
                    "{\"city\": {\n" +
                            "\"name\": \"Paris\",\n" +
                            "\"rank\": 7,\n" +
                            "\"location\": {\"lat\": 48.8588376, \"lon\": 2.2768491}\n" +
                            "}\n" +
                            "}"
                ),
                Arguments.of(
                    "{\"city\": {\n" +
                            "\"name\": \"Paris\",\n" +
                            "\"location\": {\"lat\": 48.8588376, \"lon\": 2.2768491}\n" +
                            "}\n" +
                            "}",
                    "{\"city\": {\n" +
                            "\"name\": \"Paris\",\n" +
                            "\"rank\": 7,\n" +
                            "\"location\": {\"lat\": 48.8588376, \"lon\": 2.2768491}\n" +
                            "}\n" +
                            "}"
                )
            )
    }

    @ParameterizedTest
    @MethodSource("provideTestJson")
    fun `Check the validity of the json`(input: String, expected: String) {
        val inputResponse: ParserResponse = DataParser.parseCityData(input)
        val expectedResponse: ParserResponse = DataParser.parseCityData(expected)
        assertEquals(
            "Failed $input not equals $expectedResponse",
            inputResponse,
            expectedResponse
        )
    }

}
