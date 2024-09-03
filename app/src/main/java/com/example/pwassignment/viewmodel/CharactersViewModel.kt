package com.example.pwassignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.pwassignment.data.ApiService
import com.example.pwassignment.data.repository.CharacterRepository
import com.example.pwassignment.paging.CharacterPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val apiService: ApiService) :ViewModel() {

    val pager=Pager(
        config = PagingConfig(10,5),
        pagingSourceFactory = {CharacterPagingSource(apiService =apiService )}
    ).flow.cachedIn(viewModelScope)



}