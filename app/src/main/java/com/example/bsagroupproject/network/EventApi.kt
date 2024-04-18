package com.example.bsagroupproject.network



import com.example.bsagroupproject.data.CreateEventRequest
import com.example.bsagroupproject.data.CreateEventResponse
import com.example.bsagroupproject.data.PastEventRequest
import com.example.bsagroupproject.data.PastEventResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface EventApi {
    @POST("event_CURD")
    fun getEventByYear(@Body requestData:PastEventRequest): Call<PastEventResponse>


    @POST("event_CURD")
    fun writeEvent(@Body eventData:CreateEventRequest):Call<CreateEventResponse>
}
