package com.androidstudy.branch.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.androidstudy.branch.data.dao.StockMessageDao
import com.androidstudy.branch.data.entities.StockMessage
import com.androidstudy.branch.data.remote.BranchAPI
import com.androidstudy.branch.util.NetworkResult
import com.androidstudy.branch.util.safeApiCall
import retrofit2.Retrofit
import java.io.IOException

class StockMessageRepository(
    network: Retrofit,
    private var stockMessageDao: StockMessageDao
) {
    private val apiService = network.create(BranchAPI::class.java)

    suspend fun fetchStockMessage() = safeApiCall(
        call = { getStockMessages() },
        errorMessage = "An error occurred"
    )

    private suspend fun getStockMessages(): NetworkResult<List<StockMessage>> {
        val response = apiService.getStockMessages()
        return when {
            response.isSuccessful -> {
                val stockMessageResponse = response.body()
                if (stockMessageResponse != null) {
                    saveStockMessages(stockMessageResponse)
                }
                NetworkResult.Success(response.body()!!)
            }
            else -> NetworkResult.Error(IOException("Could not get stock message"))
        }
    }

    fun fetchStockMessages(): LiveData<List<StockMessage>> = liveData {
        emit(stockMessageDao.fetchStockMessage())
    }

    private fun saveStockMessages(stockMessageList: List<StockMessage>) {

        for (stockMessage in stockMessageList) {
            stockMessageDao.insert(stockMessage)
        }
    }
}
