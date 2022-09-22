package com.example.swilehackathon.data

import com.example.swilehackathon.network.SwileService
import com.example.swilehackathon.network.model.CandidateInfo
import com.example.swilehackathon.network.model.CandidateResponse
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service: SwileService
) : Repository {

    override suspend fun sendCandidateInfo(info: CandidateInfo): com.example.swilehackathon.network.Result<CandidateResponse> {
        return service.postUserInfo(info)
    }
}