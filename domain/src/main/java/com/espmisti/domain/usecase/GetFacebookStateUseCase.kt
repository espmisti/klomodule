package com.espmisti.domain.usecase

import com.espmisti.domain.repository.FacebookRepository

class GetFacebookStateUseCase(private val repository: FacebookRepository) {
    fun execute(p0: String, p1: String) = repository.getInitState(id = p0, token = p1)
}