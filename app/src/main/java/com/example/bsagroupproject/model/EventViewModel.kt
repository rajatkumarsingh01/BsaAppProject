package com.example.bsagroupproject.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bsagroupproject.data.EventDetails
import com.example.bsagroupproject.data.EventItem
import com.example.bsagroupproject.data.EventItemForImages
import com.example.bsagroupproject.data.EventResponseForImages
import com.example.bsagroupproject.data.EventsForm
import com.example.bsagroupproject.data.PastEventRequest
import com.example.bsagroupproject.data.PastEventResponse
import com.example.bsagroupproject.network.EventApi
import com.example.bsagroupproject.network.RetrofitService
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

   class EventViewModel : ViewModel() {
       private val eventApi:EventApi=RetrofitService.eventApi
       val pastEventLiveData=MutableStateFlow(PastEventResponse())
       val pastEventDetailsData=MutableStateFlow(EventDetails(emptyMap()))
       val eventsImageData= MutableStateFlow("")
       private val _selectedYear= MutableStateFlow("")
       val selectedYear get() = _selectedYear

       private val _eventInfo= MutableStateFlow(EventsForm())
       val eventInfo get()=_eventInfo

       fun updateEventInfo(eventForm: EventsForm){
           _eventInfo.value=eventForm
       }

       fun updateSelectedYear(year:String){
           _selectedYear.value=year
       }

       fun getImagesById(request:PastEventRequest){
           eventApi.getEventImageById(request).enqueue(object :Callback<EventResponseForImages>{
               override fun onResponse(
                   call: Call<EventResponseForImages>,
                   response: Response<EventResponseForImages>
               ) {
                  if (response.isSuccessful){
                     eventsImageData.value= response.body().toString()
                      Log.d("get_Events_Images", "Events Images ${eventsImageData.value.toString()} ")

                  }else{
                      Log.e(
                          "get_Events_Images",
                          "problem getting events Images: ${response.errorBody()}, ${response.code()}"
                      )
                  }
               }

               override fun onFailure(call: Call<EventResponseForImages>, response: Throwable) {
                   Log.e("get_Events_images", "problem getting events images: ${response.message}")
               }

           })
       }
       fun parseEventDetails(requestData: PastEventRequest){
           eventApi.getEventDetails(requestData).enqueue(object :Callback<EventDetails>{
               override fun onResponse(call: Call<EventDetails>, response: Response<EventDetails>) {
                  if (response.isSuccessful){
                       pastEventDetailsData.value=response.body()!!
                      Log.d("get_Event_Details", "Event details fetched ${pastEventDetailsData.value} ")
                  }else{
                      Log.e(
                          "get_Event_Details_in",
                          "problem getting event details: ${response.errorBody()}, ${response.code()}"
                      )
                  }
               }

               override fun onFailure(call: Call<EventDetails>, response: Throwable) {
                   Log.e("get_Event_Details", "problem getting event details: ${response.message}")
               }

           })

       }



    fun getEventByYearOf(year: String) {
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
                    pastEventLiveData.value= response.body()!!
                    Log.d("get_Events_year", "Events fetched ${pastEventLiveData.value.toString()} ")
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

       //register events details on firebase realtime database

       fun registerEventsInfo(eventForm:EventsForm) {
           val currentUser = FirebaseAuth.getInstance().currentUser?.uid ?: ""
           val database = Firebase.database
           val eventRef = database.getReference("events")

           eventRef.child(currentUser).setValue(eventForm)
               .addOnSuccessListener {
                   Log.d("register_events","event details written  successfully")
               }
               .addOnFailureListener {
                   Log.d("register_events","failure writing events")
               }


       }
}

