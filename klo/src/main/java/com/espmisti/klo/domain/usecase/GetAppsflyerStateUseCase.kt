package com.espmisti.klo.domain.usecase

import com.espmisti.klo.domain.repository.AppsflyerRepository

class GetAppsflyerStateUseCase(private val repository: AppsflyerRepository) {
    suspend fun execute(v: String) = repository.getInitState(v)
}