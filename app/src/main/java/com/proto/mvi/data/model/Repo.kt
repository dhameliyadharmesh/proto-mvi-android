package com.proto.mvi.data.model

import com.google.gson.annotations.SerializedName

// Minimal fields used by the UI — add more if needed
data class Repo(
    val id: Long,
    val name: String,
    val description: String?,
    val forks: Int? = 0,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("stargazers_count") val stargazersCount: Int,
    @SerializedName("owner") val owner: Owner?,

)

data class Owner(
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)

