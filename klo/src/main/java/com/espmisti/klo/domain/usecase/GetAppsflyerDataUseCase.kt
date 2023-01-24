package com.espmisti.klo.domain.usecase

import com.espmisti.klo.domain.repository.AppsflyerRepository

class GetAppsflyerDataUseCase(private val repository: AppsflyerRepository) {
    suspend fun execute() : List<String>?{
        val data = repository.getData()
        return if (data != null) "test1_test2_test3_test4".split("_")
        else null
    }
}