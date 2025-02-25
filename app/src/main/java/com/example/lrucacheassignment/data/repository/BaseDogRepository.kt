package com.example.lrucacheassignment.data.repository

import com.example.lrucacheassignment.domain.model.DogResponse
import com.example.lrucacheassignment.util.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

interface BaseDogRepository {
    suspend fun <T> getFlowResult(
        call: suspend () -> Response<T>,
        onResult: suspend (T) -> Unit
    ): Flow<NetworkResponse<T>> =
        flow {
            emit(NetworkResponse.Loading())
            try {
                val response = call()
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        onResult(it)
                        emit(NetworkResponse.Success(it))
                    }
                } else {
                    emit(NetworkResponse.Error(response.message().toString()))
                }
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.message.toString()))
            }
        }
}
