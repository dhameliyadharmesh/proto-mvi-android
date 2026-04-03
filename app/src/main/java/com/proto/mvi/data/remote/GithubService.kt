package com.proto.mvi.data.remote

import com.proto.mvi.data.model.Repo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
	@GET("users/{username}/repos")
	suspend fun listRepos(@Path("username") username: String): Response<List<Repo>>
}


