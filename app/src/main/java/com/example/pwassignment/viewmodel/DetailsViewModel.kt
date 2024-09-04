package com.example.pwassignment.viewmodel

import android.telecom.Call.Details
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pwassignment.data.models.DetailsResponse
import com.example.pwassignment.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: CharacterRepository):ViewModel() {

    val characterDetailsResponse: MutableState<RequestState<DetailsResponse>> = mutableStateOf(RequestState.Idle)
    val data= mutableStateOf(DetailsResponse())
    fun getCharacterDetails(id:String)=viewModelScope.launch(Dispatchers.IO) {
        Log.d("Data", "getCharacterDetailsViewmodel: $id")
        repository.getCharacterDetails(id)
            .onStart {
                characterDetailsResponse.value= RequestState.Loading
            }.catch {
                characterDetailsResponse.value= RequestState.Error(it)
            }.collect{
                Log.d("ResponseData", "getCharacterDetails:${characterDetailsResponse.value} ")
                characterDetailsResponse.value= RequestState.Success(it)
            }
    }

}

sealed class RequestState<out T>{
    object Idle:RequestState<Nothing>()
    object Loading: RequestState<Nothing>()
    data class Success<T>(val data:T) :RequestState<T>()
    data class Error(val error:Throwable) :RequestState<Nothing>()
}
