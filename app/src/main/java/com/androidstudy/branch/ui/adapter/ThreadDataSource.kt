package com.androidstudy.branch.ui.adapter

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.androidstudy.branch.data.model.Chat
import com.androidstudy.branch.data.repository.ThreadRepository
import com.androidstudy.branch.util.NetworkState
import kotlinx.coroutines.*

class ThreadDataSource(
    private val repository: ThreadRepository
) : PageKeyedDataSource<Int, Chat>() {

    var loadState: MutableLiveData<NetworkState> = MutableLiveData()

    private var scope = CoroutineScope(
        Job() + Dispatchers.Default
    )

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Chat>
    ) {

        loadState.postValue(NetworkState.LOADING)

        scope.launch {
            val result = repository.fetchMessageThreads(page = 1)
            if (result == null) {
                loadState.postValue(NetworkState.error("Error getting network data"))
            } else {
                callback.onResult(result, null, 2)
                loadState.postValue(NetworkState.LOADED)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Chat>) {
        loadState.postValue(NetworkState.LOADING)

        scope.launch {
            val result = repository.fetchMessageThreads(page = params.key)
            if (result == null) {
                loadState.postValue(NetworkState.error("Error getting network data"))
            } else {
                callback.onResult(result, params.key + 1)
                loadState.postValue(NetworkState.LOADED)
            }

        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Chat>) {
        loadState.postValue(NetworkState.LOADED)
    }

    @ExperimentalCoroutinesApi
    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }
}