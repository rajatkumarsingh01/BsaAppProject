package com.example.bsagroupproject.network




import com.example.bsagroupproject.data.EventDetails
import com.example.bsagroupproject.data.EventItem
import com.example.bsagroupproject.data.EventResponseForImages
import com.example.bsagroupproject.data.PastEventRequest
import com.example.bsagroupproject.data.PastEventResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface EventApi {
    @POST("event_CURD")
    fun getEventByYear(@Body requestData: PastEventRequest): Call<PastEventResponse>

    @POST("event_CURD")
    fun getEventDetails(@Body requestData:PastEventRequest): Call<EventDetails>

    @POST("event_CURD")
    fun getEventImageById(@Body request:PastEventRequest):Call<EventResponseForImages>
}