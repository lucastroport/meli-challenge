package com.lucas.yourmarket.data.callback

interface RemoteMediatorCallback {
    suspend fun onRefreshReceived()
    fun onEmptyResponse()
    fun onErrorReceived(message: String?)
}