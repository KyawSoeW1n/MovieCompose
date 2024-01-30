package com.kurio.tetsuya.movie.compose.ui.features.upcoming.viewmodel

sealed class UpcomingEvent {
    data class SearchEvent(val keyword: String) : UpcomingEvent()
    object ResetEvent : UpcomingEvent()
}