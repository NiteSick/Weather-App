package com.example.weatherapp.repository

import com.example.weatherapp.Model.City
import com.example.weatherapp.Network.ApiServiceImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiServiceImp: ApiServiceImp) {

    fun getCityDAta(city:String) : Flow<City> = flow {
        val response = apiServiceImp.getCityData(city,"b9cfbbea0dbff6c2b643f1c58a2bef90")
        emit(response)
    }.flowOn(Dispatchers.IO).conflate()


}