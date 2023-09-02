package com.example.natifetest.data.repository

import com.example.natifetest.data.api.RetrofitInstance
import com.example.natifetest.model.GifModel

class GifRepository {
    suspend fun getGifs(): GifModel {
        return RetrofitInstance.api.getTrendingGifs()
    }
}