package com.hackathon.data.dto

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.hackathon.data.dto.Dimensions.MediumPadding1

@Composable
fun EventList(
    modifier: Modifier,
    events:LazyPagingItems<Data>,
    onClick:()->Unit
) {

    val handlePagingResult = handlePagingResult(events)

    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            contentPadding = PaddingValues(all = 4.dp)
        ) {
            items(
                count = events.itemCount,
            ) {
                events[it]?.let { event ->
                    EventCard(

                        event = event
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}


@Composable
fun handlePagingResult(events: LazyPagingItems<Data>): Boolean {
    val loadState=events.loadState
    val error = when{
        loadState.refresh is LoadState.Error ->loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when{
        loadState.refresh is LoadState.Loading ->{
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}

@Composable
fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding1)){
        repeat(10){
            EventCardShimmerEffect(
                modifier = Modifier.padding(horizontal = MediumPadding1)
            )
        }
    }
}


