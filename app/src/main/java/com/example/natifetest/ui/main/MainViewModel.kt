package com.example.natifetest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.natifetest.data.repository.GifRepository
import com.example.natifetest.model.Data
import com.example.natifetest.model.GifModel
import kotlinx.coroutines.launch

class MainViewModel(
    private val gifRepository: GifRepository
) : ViewModel() {

    private val _gifs = MutableLiveData<GifModel>()
    val gifs: LiveData<GifModel> = _gifs

    fun getGifs() {
        viewModelScope.launch {
            _gifs.value = gifRepository.getGifs()
        }
    }

    init {
        getGifs()
    }
}