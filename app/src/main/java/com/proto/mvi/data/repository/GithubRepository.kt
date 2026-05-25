package com.proto.mvi.data.repository

import com.proto.mvi.data.model.Repo
import com.proto.mvi.data.remote.GithubService
import com.proto.mvi.util.ResultWrapper
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(
    private val service: GithubService
) {
    suspend fun getUserRepos(username: String): ResultWrapper<List<Repo>> {
        return try {
            val response = service.listRepos(username)
            if (response.isSuccessful) {
                val body = response.body() ?: emptyList()
                ResultWrapper.Success(body)
            } else {
                ResultWrapper.ApiError(response.code(), response.message())
            }
        } catch (e: IOException) {
            // network error
            ResultWrapper.NetworkError(e)
        } catch (e: HttpException) {
            ResultWrapper.ApiError(e.code(), e.message())
        } catch (e: Exception) {
            ResultWrapper.UnknownError(e)
        }
    }
}

