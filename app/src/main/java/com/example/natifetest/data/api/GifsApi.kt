package com.example.natifetest.data.api

import com.example.natifetest.utils.API_KEY
import com.example.natifetest.model.Data
import com.example.natifetest.model.GifModel
import retrofit2.http.GET
import retrofit2.http.Query

interface GifsApi {

    @GET("trending")
    suspend fun getTrendingGifs(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("limit") limit: Int = 25,
        @Query("offset") offset: Int = 0,
        @Query("rating") rating: String = "g",
        @Query("bundle") bundle: String = "messaging_non_clips"
    ): GifModel

}