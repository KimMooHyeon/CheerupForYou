package com.sabuzak.yeonamplace.cheerupforyou.presentation.makingcheerup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sabuzak.yeonamplace.cheerupforyou.data.model.Banner
import com.sabuzak.yeonamplace.cheerupforyou.data.main.HomeLocalDataSource
import com.sabuzak.yeonamplace.cheerupforyou.data.makingcheeruptext.MakingCheerUpTextLocalDataSource
import com.sabuzak.yeonamplace.cheerupforyou.presentation.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MakingCheerUpTextViewModel @Inject constructor(
    private val repository: MakingCheerUpTextLocalDataSource
) : ViewModel() {
    private val _eventFlow = MutableSharedFlow<Event<MakingCheerUpText>>()
    val eventFlow: SharedFlow<Event<MakingCheerUpText>> = _eventFlow.asSharedFlow()

    private fun event(event: MakingCheerUpText) {
        viewModelScope.launch {
            _eventFlow.emit(Event(event))
        }
    }

    fun insert(banner: Banner) = viewModelScope.launch {
           repository.insert(banner)
    }
    fun update(banner: Banner) = viewModelScope.launch {
        repository.update(banner)
    }
}

sealed class MakingCheerUpText {
    data class NextButtonResult(val dateAndVideoModelItem: Boolean) : MakingCheerUpText()
    data class BackButtonResult(val dateModel: Boolean) : MakingCheerUpText()
}
