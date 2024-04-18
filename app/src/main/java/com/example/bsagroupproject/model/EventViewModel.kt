package com.example.bsagroupproject.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bsagroupproject.data.CreateEventRequest
import com.example.bsagroupproject.data.CreateEventResponse
import com.example.bsagroupproject.data.PastEventRequest
import com.example.bsagroupproject.data.PastEventResponse
import com.example.bsagroupproject.network.EventApi
import com.example.bsagroupproject.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

   class EventViewModel : ViewModel() {
       private val eventApi:EventApi=RetrofitService.eventApi
       val pastEventLiveData: MutableLiveData<PastEventResponse?> = MutableLiveData()
        val createEventLiveData: MutableLiveData<CreateEventResponse> = MutableLiveData()

    fun getEventByYear(year: String) {
        val requestData = PastEventRequest(
            uID = "try",
            country = "IN",
            action = "read",
            queryType = "Year",
            queryValue = year
        )

        eventApi.getEventByYear(requestData).enqueue(object : Callback<PastEventResponse> {
            override fun onResponse(
                call: Call<PastEventResponse>,
                response: Response<PastEventResponse>
            ) {
                if (response.isSuccessful) {
                    pastEventLiveData.postValue(response.body())
                    Log.d("get_Events_year", "Events fetched ")
                } else {
                    Log.e(
                        "get_Events_year",
                        "problem getting events: ${response.errorBody()}, ${response.code()}"
                    )

                }
            }

            override fun onFailure(call: Call<PastEventResponse>, response: Throwable) {
                Log.e("get_Events_year", "problem getting events: ${response.message}")
            }

        })
    }

    //Creating event
    fun createEvent(eventData: CreateEventRequest) {
        eventApi.writeEvent(eventData).enqueue(object : Callback<CreateEventResponse> {
            override fun onResponse(
                call: Call<CreateEventResponse>,
                response: Response<CreateEventResponse>
            ) {
                if (response.isSuccessful) {
                    createEventLiveData.postValue(response.body())
                    Log.d("create_events", "Events Created")
                } else {
                    Log.e(
                        "create_events",
                        "problem creating events: ${response.errorBody()}, ${response.code()}"
                    )
                }
            }

            override fun onFailure(call: Call<CreateEventResponse>, response: Throwable) {
                Log.e("create_events", "problem creating events: ${response.message}")
            }

        })

    }


}

