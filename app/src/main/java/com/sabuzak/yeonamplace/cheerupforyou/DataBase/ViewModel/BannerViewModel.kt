package com.sabuzak.yeonamplace.cheerupforyou.DataBase.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sabuzak.yeonamplace.cheerupforyou.DataBase.AppDatabase
import com.sabuzak.yeonamplace.cheerupforyou.DataBase.Entity.Banner
import com.sabuzak.yeonamplace.cheerupforyou.DataBase.Repository.BannerRepository
import kotlinx.coroutines.launch

class BannerViewModel(application: Application): AndroidViewModel(application) {

    private val repository:BannerRepository
    var allBanner:LiveData<List<Banner>>

    init {
        val bannerDao = AppDatabase.getDatabase(application).bannerDao()
        repository = BannerRepository(bannerDao)
        allBanner = repository.allBanner
    }
    fun insert(banner:Banner) = viewModelScope.launch{
        repository.insert(banner)
    }
}