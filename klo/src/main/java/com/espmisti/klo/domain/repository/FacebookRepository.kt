package com.espmisti.klo.domain.repository

interface FacebookRepository {
    fun getInitState(id: String, token: String) : Boolean
    suspend fun getData() : String?
}