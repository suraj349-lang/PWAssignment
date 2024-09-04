package com.example.pwassignment.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pwassignment.data.ApiService
import com.example.pwassignment.data.models.Character

import retrofit2.HttpException
import java.io.IOException


class CharacterPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getCharactersList(page)
            if(response.isSuccessful){
                val body=response.body()?.results ?: emptyList()
                LoadResult.Page(
                    data = body,
                    prevKey = if(page==1) null else page-1,
                nextKey = if(body.isEmpty()) null else page+1
             )
            }else{
                LoadResult.Error(Exception())
            }
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}