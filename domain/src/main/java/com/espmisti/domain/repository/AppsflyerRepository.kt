package com.espmisti.domain.repository

interface AppsflyerRepository {
    suspend fun getInitState(key: String) : Boolean
    suspend fun getData() : MutableMap<String, Any>?

    fun init(key: String)
}