package com.hackathon.emergency

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hackathon.data.dto.DisastersApi

class ContactsPagingSource (
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
            val eventsResponse=disastersApi.getContact()
            totalNewsCount=eventsResponse.data.size

            val datas=eventsResponse.data
            LoadResult.Page(
                data=datas,
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