package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.SearchView.*
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.ViewModel.WeatherViewModel
import com.example.weatherapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val weatherViewModel : WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()

        weatherViewModel.getCityData()
        weatherViewModel.getData.observe(this, Observer { response->



            if(response.weather.description == "clear sky" || response.weather.description == "mist"){
                Glide.with(this)
                    .load(R.drawable.cloud)
                    .into(binding.image)
            }else
                if(response.weather.description == "haze" || response.weather.description == "overcast clouds" || response.weather.description == "fog" ){
                    Glide.with(this)
                        .load(R.drawable.haze)
                        .into(binding.image)
                }else
                    if(response.weather.description == "rain"){
                        Glide.with(this)
                            .load(R.drawable.rainy)
                            .into(binding.image)
                    }
            binding.description.text=response.weather.description
            binding.name.text=response.name
            binding.degree.text=response.wind.degree.toString()
            binding.speed.text=response.wind.speed.toString()
            binding.temp.text=response.main.temp.toString()
            binding.humidity.text=response.main.humidity.toString()

        })
    }

    private fun initListener(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                p0?.let {
                    weatherViewModel.getSearchData(it)
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

        })
    }
}