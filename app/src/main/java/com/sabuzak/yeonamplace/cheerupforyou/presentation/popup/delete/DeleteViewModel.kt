package com.sabuzak.yeonamplace.cheerupforyou.presentation.popup.delete

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sabuzak.yeonamplace.cheerupforyou.data.deletepopup.DeletePopUpRepository
import com.sabuzak.yeonamplace.cheerupforyou.data.model.Banner
import com.sabuzak.yeonamplace.cheerupforyou.data.main.HomeLocalDataSource
import com.sabuzak.yeonamplace.cheerupforyou.presentation.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteViewModel @Inject constructor(
    private val repository: DeletePopUpRepository
) : ViewModel() {
    private val _eventFlow = MutableSharedFlow<Event<DeletePopUpEvent>>()
    val eventFlow: SharedFlow<Event<DeletePopUpEvent>> = _eventFlow.asSharedFlow()

    private fun event(event: DeletePopUpEvent) {
        viewModelScope.launch {
            _eventFlow.emit(Event(event))
        }
    }

    fun deleteByIdx(bannerIdx: Int) = viewModelScope.launch {
        repository.deleteByIdx(bannerIdx)
    }
}

sealed class DeletePopUpEvent {
}
