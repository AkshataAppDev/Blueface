package com.example.bluefacecodingchallenge.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface AppDispatchers {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
}

class AppDispatchersImpl :
    AppDispatchers {
    override val main: CoroutineDispatcher = Dispatchers.Main
    override val io: CoroutineDispatcher = Dispatchers.IO
}
