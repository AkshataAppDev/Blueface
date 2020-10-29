package com.example.bluefacecodingchallenge.ui.activity

import androidx.lifecycle.MutableLiveData
import com.example.bluefacecodingchallenge.dispatcher.AppDispatchers
import com.example.bluefacecodingchallenge.database.CityEntity
import com.example.bluefacecodingchallenge.model.CityLatLng
import com.example.bluefacecodingchallenge.repositories.CityRepo
import com.example.bluefacecodingchallenge.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val cityRepo: CityRepo,
    appDispatchers: AppDispatchers
) : BaseViewModel(appDispatchers) {

    private val latLng = MutableLiveData<CityLatLng>()
    var cities = MutableLiveData<MutableList<CityEntity>>()

    init {
        launch {
            try {
                cities.postValue(cityRepo.getAllCities() as MutableList<CityEntity>?)
            } catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun insertNewCity(city: CityEntity) {
        latLng.let {
            city.citylat = it.value?.lat!!
            city.citylon = it.value?.lon!!
        }.apply {
            cityRepo.saveCity(city)
            cities.value?.add(city)
        }
    }

    fun setLatLng(latitude: Double, longitude: Double) {
        latLng.value = CityLatLng(latitude, longitude)
    }

}