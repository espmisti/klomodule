package com.espmisti.klo.domain.usecase

import com.espmisti.klo.domain.repository.FacebookRepository

class GetFacebookStateUseCase(private val repository: FacebookRepository) {
    fun execute(p0: String, p1: String) = repository.getInitState(id = p0, token = p1)
}