package com.hackathon.data.dto

import androidx.paging.PagingSource
import androidx.paging.PagingState

class NewsPagingSource (
    private val disastersApi: DisastersApi
): PagingSource<Int, Data>() {

    private var totalNewsCount=0

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPage ->
            val page = state.closestPageToPosition(anchorPage)
            page?.nextKey?.minus(1) ?: page?.prevKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val page=params.key?:1
        return try {
            val eventsResponse=disastersApi.getEvents()
            totalNewsCount=eventsResponse.data.size
            val events=eventsResponse.data.distinctBy { it.title }
            LoadResult.Page(
                data=events,
                prevKey = null,
                nextKey = if(totalNewsCount==eventsResponse.totalResults) null else page+1
            )
        }catch (e:Exception){
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }

}