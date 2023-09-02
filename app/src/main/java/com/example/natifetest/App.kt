package com.example.natifetest

import android.app.Application
import com.example.natifetest.data.repository.GifRepository

class App: Application() {
    val gifRepository = GifRepository()
}