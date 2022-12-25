package com.sabuzak.yeonamplace.cheerupforyou.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sabuzak.yeonamplace.cheerupforyou.data.model.Banner
import com.sabuzak.yeonamplace.cheerupforyou.data.main.HomeRepository
import com.sabuzak.yeonamplace.cheerupforyou.presentation.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {
    private val _eventFlow = MutableSharedFlow<Event<HomeEvent>>()
    val eventFlow: SharedFlow<Event<HomeEvent>> = _eventFlow.asSharedFlow()
    private var removeFlag = false

    private fun event(event: HomeEvent) {
        viewModelScope.launch {
            _eventFlow.emit(Event(event))
        }
    }

    fun getAllBanner() = repository.getAll()

    fun insert(banner: Banner) = viewModelScope.launch {
        repository.insert(banner)
    }

    fun delete(banner: Banner) = viewModelScope.launch {
        repository.delete(banner)
    }

    fun navigateToMakeCheerUpTextView() {

        event(HomeEvent.NavigateToMakeCheerUpTextView)
    }

    fun navigateToRoadToKingdomView() {
        event(HomeEvent.NavigateToRoadToKingdomView)
    }

    fun navigateToRequestPopUpView() {
        event(HomeEvent.NavigateToRequestPopUpView)
    }

    fun shareToFriend() {
        event(HomeEvent.ShareToFriend)
    }

    fun removeBanner() {
        removeFlag = !removeFlag
        event(HomeEvent.RemoveBanner(removeFlag))
    }
}

sealed class HomeEvent {
    object NavigateToMakeCheerUpTextView : HomeEvent()
    object NavigateToRoadToKingdomView : HomeEvent()
    object NavigateToRequestPopUpView : HomeEvent()
    object ShareToFriend : HomeEvent()
    data class RemoveBanner(val removeFlag: Boolean) : HomeEvent()
}
