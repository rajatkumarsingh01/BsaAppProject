package com.example.bsagroupproject.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val BASE_URL="https://us-central1-bsaclubvivek.cloudfunctions.net/"

    val retrofit= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val eventApi:EventApi= retrofit.create(EventApi::class.java)
}