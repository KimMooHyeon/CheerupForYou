package com.sabuzak.yeonamplace.cheerupforyou.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sabuzak.yeonamplace.cheerupforyou.data.model.Banner
import com.sabuzak.yeonamplace.cheerupforyou.data.dataBase.AppDatabase
import com.sabuzak.yeonamplace.cheerupforyou.data.repository.BannerRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository: BannerRepository
    var allBanner:LiveData<List<Banner>>

    init {
        val bannerDao = AppDatabase.getDatabase(application).bannerDao()
        repository = BannerRepository(bannerDao)
        allBanner = repository.allBanner
    }
    fun insert(banner:Banner) = viewModelScope.launch{
        repository.insert(banner)
    }
    fun delete(banner: Banner) = viewModelScope.launch {
        repository.delete(banner)
    }
}