package com.lucas.yourmarket.data.callback

interface RemoteMediatorCallback {
    fun onRefreshReceived()
    fun onEmptyResponse()
    fun onErrorReceived()
}