package com.example.coroutine_practice.multiplerequest.concurrency

import androidx.lifecycle.viewModelScope
import com.example.coroutine_practice.base.BaseViewModel
import com.example.coroutine_practice.common.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext

data class Loading(
    var loadingBanner: Boolean = true,
    var loadingLatestProduct: Boolean = true,
)

/**
 * Multiple request - Concurrency
 */
class ConcurrencyViewModel : BaseViewModel() {
    private var parentJob: Job? = null

    override fun fetchData() {
        parentJob = viewModelScope.launch(parentExceptionHandler) {
            Logger.log("Show Loading")
//            failAndRetry()
            val result = repository.requestWithIndex(1)
//            repository.saveToDb(result*6) // bị cancel khi back khỏi fragment (viewmodel bị destroy)

            withContext(NonCancellable){
                repository.saveToDb(result*6)
            }
        }

        parentJob?.invokeOnCompletion {// khi parent job kết thúc
            // show error
            Logger.log("Hide Loading")
        }
    }

    /**
     * Cancel all job
     */
    fun stopAll() {
        parentJob?.cancel()
    }

    /**
     * Independent request (Cái nào trả về trước -> hiển thị trước, không phụ thuộc nhau)
     */
    private suspend fun independentRequest() {
        supervisorScope {
            launch(parentExceptionHandler) {
                repository.requestWithIndex(1, true) // fail
            } // => supervisor job

            launch {
                repository.requestWithIndex(2) // still running
            } // => supervisor job
        }
    }

    /**
     * Fail And Retry
     */
    private suspend fun failAndRetry(): Int {
        repeat(3) {
            try {
                return repository.requestWithIndex(2, true)
            } catch (e: Exception) {
                Logger.log("Retry ${e.message}")
            }
        }
        return repository.requestWithIndex(2)
    }

    /**
     * request concurrency
     */
    private suspend fun requestConcurrency() {
        viewModelScope.launch {
            launch {
                repository.requestWithIndex(1)
            }
            launch {
                repository.requestWithIndex(2)
            }
            launch {
                repository.requestWithIndex(3)
            }
        }
    }

    /**
     * Wait all job for results
     */
    private suspend fun waitAllJobsForResult() {
        viewModelScope.launch {
            val job1 = async { // concurrency
                repository.requestWithIndex(1)
            }
            val job2 = async { // concurrency
                repository.requestWithIndex(2)
            }
            val job3 = async { // concurrency
                repository.requestWithIndex(3)
            }
            Logger.log("Start")
            val listJobs = listOf(job1, job2, job3).awaitAll() // giá trị của 3 jobs
            Logger.log("End")
            listJobs.forEach {
                Logger.log("Result: $it")
            }
        }
    }


    /**
     * Cancelable and NonCancelable
     */
    private suspend fun saveToDB() {

    }

}
/**
 * Deferred is child of Job
 */