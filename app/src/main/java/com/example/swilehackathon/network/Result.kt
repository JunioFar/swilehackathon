package com.example.swilehackathon.network

sealed class Result<T> {
    class Success<T>(val data: T) : Result<T>()
    class Failure<T>(val error: String) : Result<T>()
}