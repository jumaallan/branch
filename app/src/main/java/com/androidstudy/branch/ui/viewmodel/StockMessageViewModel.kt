package com.androidstudy.branch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidstudy.branch.data.entities.StockMessage
import com.androidstudy.branch.data.repository.StockMessageRepository
import com.androidstudy.branch.util.NetworkResult
import com.androidstudy.branch.util.livedata.NonNullMediatorLiveData
import kotlinx.coroutines.launch

class StockMessageViewModel(stockMessageRepository: StockMessageRepository) : ViewModel() {

    private var repo = stockMessageRepository
    private val stockMessageMediatorLiveData = NonNullMediatorLiveData<List<StockMessage>>()
    private val stockMessageError = NonNullMediatorLiveData<String>()

    fun fetchStockMessages(): LiveData<List<StockMessage>> {
        return repo.fetchStockMessages()
    }

    fun getStockMessageThreads() {
        viewModelScope.launch {
            when (val value = repo.fetchStockMessage()) {
                is NetworkResult.Success -> stockMessageMediatorLiveData.postValue(value.data)
                is NetworkResult.Error -> stockMessageError.postValue(value.exception.message)
            }
        }
    }

    fun getStockMessageResponse(): LiveData<List<StockMessage>> = stockMessageMediatorLiveData

    fun getStockMessageError(): LiveData<String> = stockMessageError
}
