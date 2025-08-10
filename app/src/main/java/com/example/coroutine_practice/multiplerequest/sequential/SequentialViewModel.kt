package com.example.coroutine_practice.multiplerequest.sequential

import androidx.lifecycle.viewModelScope
import com.example.coroutine_practice.base.BaseViewModel
import com.example.coroutine_practice.common.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * Multiple request - Sequential
 */
class SequentialViewModel: BaseViewModel() { // 1 thằng fail -> tất cả fail
    override fun fetchData() {
        viewModelScope.launch { // main thread
            Logger.log("Show loading ${Thread.currentThread().name}")
            // do smt -> show loading
            withContext(Dispatchers.IO) {
                repository.requestWithIndex(1)
                repository.requestWithIndex(2)
                // io thread
                // done
            }
//            launch(Dispatchers.IO){
//                // run cùng main thread
//            }
            // hide loading -> oke
            Logger.log("Hide loading")

        }
    }
}