package com.example.natifetest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.natifetest.data.GifPagingSource
import com.example.natifetest.data.api.RetrofitInstance
import com.example.natifetest.model.Data
import com.example.natifetest.model.GifModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GifRepository(
    private val ioDispatcher: CoroutineDispatcher,
) {

    fun getPagedGifs(): Flow<PagingData<Data>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { GifPagingSource(this, PAGE_SIZE) }
        ).flow
    }

    suspend fun getGifs(): GifModel = withContext(ioDispatcher) {
        return@withContext RetrofitInstance.api.getTrendingGifs()
    }

    companion object {
        private const val PAGE_SIZE = 25
    }
}