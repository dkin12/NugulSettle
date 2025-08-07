package com.nugul.settle.dataclass

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("/users/login")
    suspend fun login(@Body request: LoginRequest): Response<UserResponse>

    @POST("/users")
    suspend fun signUp(@Body user: UserResponse): Response<UserResponse>
}