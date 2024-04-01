package com.example.myflix.core.data.source.remote.dto.response

import com.example.myflix.core.domain.model.Detail
import com.example.myflix.core.domain.model.User

data class UserResponse(
    val id: String? = null,
    val detail: DetailResponse? = null
)

data class DetailResponse(
    val gender: String? = null,
    val watchList: List<String>? = null,
    val birthDate: String? = null,
    val email: String? = null,
    val username: String? = null
)

fun UserResponse.toDomain() = User(
    id,
    Detail(
        detail?.gender,
        detail?.watchList,
        detail?.birthDate,
        detail?.email,
        detail?.username,
        "https://avatar.iran.liara.run/public?username=${detail?.username}"
    )
)