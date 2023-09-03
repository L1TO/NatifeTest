package com.example.natifetest.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.natifetest.data.repository.GifRepository
import com.example.natifetest.model.Data
import com.example.natifetest.model.GifModel

class GifPagingSource(
    private val gifRepository: GifRepository,
    private val pageSize: Int,
) : PagingSource<Int, Data>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {

        return try {
            val position = params.key ?: 0
            val gifs = gifRepository.getGifs()

            return LoadResult.Page(
                data = gifs.data,
                prevKey = null,
                nextKey = if (params.loadSize == gifs.data.size) position + (params.loadSize / pageSize) else null
            )
        } catch (e: Exception) {
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition
    }

}