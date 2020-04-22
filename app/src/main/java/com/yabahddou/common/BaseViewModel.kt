package com.yabahddou.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext = job + Dispatchers.Main

    /**
     * Simplify calling coroutines
     */
    protected fun uiCoroutine(blockToRun: suspend () -> Unit) =
        CoroutineScope(Dispatchers.Main).launch(coroutineContext) {
            blockToRun()
        }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}