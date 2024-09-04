package com.example.pwassignment.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pwassignment.data.ApiService
import com.example.pwassignment.data.models.Character
import com.example.pwassignment.data.models.DetailsResponse
import com.example.pwassignment.paging.CharacterPagingSource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ViewModelScoped
class CharacterRepository @Inject constructor(private val apiService: ApiService){

    fun getCharacterDetails(id:String): Flow<DetailsResponse> = flow  {
        Log.d("Data", "getCharacterDetailsRepo: $id")
        emit(apiService.getCharacterDetails(id.toInt()))
    }.flowOn(Dispatchers.IO)

}