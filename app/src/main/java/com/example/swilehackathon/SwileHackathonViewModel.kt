package com.example.swilehackathon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swilehackathon.data.Repository
import com.example.swilehackathon.network.Result
import com.example.swilehackathon.network.model.CandidateInfo
import com.example.swilehackathon.network.model.CandidateResponse
import com.example.swilehackathon.util.CPFValidator
import com.example.swilehackathon.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SwileHackathonViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val candidateLiveData = MutableLiveData<Event<CandidateResponse>>()
    val errorLiveDate = MutableLiveData<Event<String>>()

    fun postUserInfo(document: String, base64: String, jwt: String) {
        viewModelScope.launch {
            when(val result = repository.sendCandidateInfo(CandidateInfo(document, base64, jwt))) {
                is Result.Success -> candidateLiveData.value = Event(result.data)
                is Result.Failure -> errorLiveDate.value = Event("Algo deu errado!!!")
            }
        }
    }

    fun isCPFValid(document: String): Boolean {
        return CPFValidator.isCPFValid(document)
    }

}