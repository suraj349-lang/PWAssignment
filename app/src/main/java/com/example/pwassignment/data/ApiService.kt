package com.example.pwassignment.data

import com.example.pwassignment.data.models.CharacterResponse
import com.example.pwassignment.data.models.DetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/character")
    suspend fun getCharactersList(@Query("page") page:Int): Response<CharacterResponse>

    @GET("/api/character/{id}")
    suspend fun getCharacterDetails(@Query("id") id:Int): DetailsResponse

}