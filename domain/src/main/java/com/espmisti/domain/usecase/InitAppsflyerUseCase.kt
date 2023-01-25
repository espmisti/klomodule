package com.espmisti.domain.usecase

import com.espmisti.domain.repository.AppsflyerRepository

class InitAppsflyerUseCase(private val repository: AppsflyerRepository) {
    fun execute(key: String) {
        repository.init(key)
    }
}