package com.example.swilehackathon.network

import com.example.swilehackathon.network.Result
import com.example.swilehackathon.network.model.CandidateInfo
import com.example.swilehackathon.network.model.CandidateResponse
import com.example.swilehackathon.util.ResultWrapper
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Inject

class SwileService @Inject constructor(val api: API) {

    interface API {
        @POST("test/test")
        suspend fun postUserInfo(@Body userInfo: CandidateInfo): CandidateResponse
    }

    suspend fun postUserInfo(userInfo: CandidateInfo) = ResultWrapper.wrapResult { api.postUserInfo(userInfo) }
}