package com.example.coroutine_practice.singlerequest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.coroutine_practice.base.BaseViewModel
import com.example.coroutine_practice.common.Logger
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


/**
 * Single Request
 *   + Make a request and receive the data - Success
 *   + Make a request and handle the exception - Fail
 *   + Make a request and cancel it - Cancel
 */
class SingleViewModel : BaseViewModel() {
    var data = MutableLiveData<Int>()
        private set

    private lateinit var parentJob: Job

    override fun fetchData() {
        parentJob = viewModelScope.launch(parentExceptionHandler) {
            val deferred = async {
                repository.requestWithIndex(4, true)
            }
            val result = deferred.await()

            data.postValue(result)
        }

        parentJob.invokeOnCompletion {
            Logger.log("Parent Job is finished")
        }
    }

    fun cancelJob(){
        parentJob.cancel()
    }
}