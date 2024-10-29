package com.matrimony.rvrmatrimony.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

data class CarResponse(
    val Car: CarDetails
)

data class CarDetails(
    val id: Int,
    val car: String,
    val car_model: String,
    val car_color: String,
    val car_model_year: Int,
    val car_vin: String,
    val price: String,
    val availability: Boolean
)

interface CarApiService {
    @GET("api/cars/{id}")
    suspend fun getCarById(@Path("id") id: Int): Response<CarResponse>
}