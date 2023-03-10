package com.espmisti.domain.usecase

import com.espmisti.domain.repository.FacebookRepository

class GetFacebookDataUseCase(private val repository: FacebookRepository) {
    suspend fun execute() : List<String>? {
        val data = repository.getData()
        return if (data != null) {
            if (data.contains("?")) {
                val result = data.toString().substringAfter("//").substringBefore("?")
                result.split("_")
            } else {
                val result = data.toString().substringAfter("//")
                result.split("_")
            }
        } else null
    }
}