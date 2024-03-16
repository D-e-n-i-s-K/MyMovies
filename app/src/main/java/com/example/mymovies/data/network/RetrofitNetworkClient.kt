package com.example.mymovies.data.network

import com.example.mymovies.data.NetworkClient
import com.example.mymovies.data.dto.MoviesSearchRequest
import com.example.mymovies.data.dto.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitNetworkClient: NetworkClient {

    private val imdbBaseUrl = "https://tv-api.com"
    private val retrofit = Retrofit.Builder()
        .baseUrl(imdbBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val imdbService = retrofit.create(IMDbApiService::class.java)
    override fun doRequest(dto: Any): Response {
        if (dto is MoviesSearchRequest){
            val response = imdbService.searchMovies(dto.expression).execute()
            val body = response.body() ?: Response()
            return body.apply { resultCode = response.code() }
        }else{
            return Response().apply { resultCode = 400 }
        }
    }
}