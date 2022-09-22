package com.example.swilehackathon.data

import com.example.swilehackathon.network.Result
import com.example.swilehackathon.network.model.CandidateInfo
import com.example.swilehackathon.network.model.CandidateResponse

interface Repository {

    suspend fun sendCandidateInfo(info: CandidateInfo): Result<CandidateResponse>
}