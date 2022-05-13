package com.example.ViewModel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Model.City
import com.example.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch


class WeatherViewModel constructor(private val weatherRepository: WeatherRepository): ViewModel(){

    val getData:MutableLiveData<City> = MutableLiveData()
    val searchCity = ConflatedBroadcastChannel<String>()

    fun getSearchData(city:String){
        searchCity.trySend(city)
    }

    @OptIn(ObsoleteCoroutinesApi::class)
    fun getCityData()= viewModelScope.launch {
        searchCity.asFlow().flatMapLatest {
            city -> weatherRepository.getCityDAta(city)
        }.catch { e->
            Log.d("main","getCityData:")
        }.collect{city->
            getData.value=city

        }
    }

}