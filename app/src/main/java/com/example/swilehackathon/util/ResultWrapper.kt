package com.example.swilehackathon.util

import java.io.IOException

object ResultWrapper {
    suspend fun <T> wrapResult(invokeApiCall: suspend () -> T): com.example.swilehackathon.network.Result<T> {
        return try {
            val responseData = invokeApiCall()
            com.example.swilehackathon.network.Result.Success(responseData)
        } catch (e: Exception) {
            when (e) {
                //Needs to cover other scenarios
                is IOException -> com.example.swilehackathon.network.Result.Failure("")
                else -> com.example.swilehackathon.network.Result.Failure("")
            }
        }
    }
}