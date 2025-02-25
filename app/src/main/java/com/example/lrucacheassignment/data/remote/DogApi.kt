package com.example.lrucacheassignment.data.remote

import com.example.lrucacheassignment.domain.model.DogResponse
import retrofit2.Response
import retrofit2.http.GET

interface DogApi {
    @GET("api/breeds/image/random")
    suspend fun getDog(): Response<DogResponse>
}