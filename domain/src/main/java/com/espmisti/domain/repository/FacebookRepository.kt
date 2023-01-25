package com.espmisti.domain.repository

interface FacebookRepository {
    fun getInitState(id: String, token: String) : Boolean
    suspend fun getData() : String?
}