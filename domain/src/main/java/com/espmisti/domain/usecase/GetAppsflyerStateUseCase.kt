package com.espmisti.domain.usecase

import com.espmisti.domain.repository.AppsflyerRepository

class GetAppsflyerStateUseCase(private val repository: AppsflyerRepository) {
    suspend fun execute(v: String) = repository.getInitState(v)
}