package com.example.bluefacecodingchallenge.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.bluefacecodingchallenge.di.ViewModelProviderFactory
import com.google.android.gms.location.*
import javax.inject.Inject
import com.example.bluefacecodingchallenge.ui.base.BaseActivity
import com.example.bluefacecodingchallenge.R
import com.example.bluefacecodingchallenge.dataSource.JsonData
import com.example.bluefacecodingchallenge.model.CityData
import com.example.bluefacecodingchallenge.parser.DataParser
import com.example.bluefacecodingchallenge.parser.ErrorType
import com.example.bluefacecodingchallenge.parser.ParserResponse


class MainActivity : BaseActivity() {
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var viewModel: MainViewModel
    lateinit var cityData: CityData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        viewModel = ViewModelProvider(
            this, providerFactory
        ).get(MainViewModel::class.java)

        parseData()

        getLastLocation()
    }

    override fun setLatLng(latitude: Double, longitude: Double) {
        viewModel.setLatLng(latitude, longitude)
    }

    private fun parseData() {
        JsonData().paris.let { json ->
            when (val response = DataParser.parseCityData(json)) {
                is ParserResponse.Success -> cityData = response.cityData
                is ParserResponse.Error -> handleError(response.message, response.type)
            }
        }
    }

    private fun handleError(message: String, errorType: ErrorType) {
        when (errorType) {
            ErrorType.NullOrEmpty -> Log.d("$TAG", "Error type: $errorType, $message")
            ErrorType.NotValid -> Log.d("$TAG", "Error type: $errorType, $message")
        }
    }

    companion object {
        const val TAG = "MainActivity"
        const val PERMISSION_ID = 42
    }

}