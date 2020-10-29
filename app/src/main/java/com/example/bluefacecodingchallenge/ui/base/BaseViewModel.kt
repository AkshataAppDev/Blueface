package com.example.bluefacecodingchallenge.ui.base

import androidx.lifecycle.ViewModel
import com.example.bluefacecodingchallenge.dispatcher.AppDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel constructor(
    private val dispatchers: AppDispatchers
) : ViewModel(), CoroutineScope {

    private val job: Job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = dispatchers.main + job


    fun dispose(){
        job.cancelChildren()
    }
}