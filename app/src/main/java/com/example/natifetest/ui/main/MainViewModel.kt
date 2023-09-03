package com.example.natifetest.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.natifetest.data.repository.GifRepository
import com.example.natifetest.model.Data
import kotlinx.coroutines.flow.Flow

class MainViewModel(
    private val gifRepository: GifRepository
) : ViewModel() {
    var gifs: Flow<PagingData<Data>>? = null

    private fun getGifs() {
        gifs = gifRepository.getPagedGifs().cachedIn(viewModelScope)
    }

    fun refresh() {
        val _gifs = gifRepository.getPagedGifs().cachedIn(viewModelScope)
        gifs = _gifs
    }

    init {
        getGifs()
    }

}