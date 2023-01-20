package com.espmisti.klo.domain.usecase

import com.espmisti.klo.domain.repository.AppsflyerRepository

class GetAppsflyerDataUseCase(private val repository: AppsflyerRepository) {
    suspend fun execute() : String?{
        val data = repository.getData()
        return if (data != null) data["campaign"].toString()
        else null
    }
}