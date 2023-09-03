package com.example.natifetest

import android.app.Application
import com.example.natifetest.data.repository.GifRepository
import kotlinx.coroutines.Dispatchers

class App: Application() {
    val gifRepository = GifRepository(Dispatchers.IO)
}