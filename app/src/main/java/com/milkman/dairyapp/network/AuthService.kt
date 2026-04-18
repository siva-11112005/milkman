package com.milkman.dairyapp.network

import com.milkman.dairyapp.network.models.AdminResponse
import com.milkman.dairyapp.network.models.AdminDetailsResponse
import com.milkman.dairyapp.network.models.LoginRequest
import com.milkman.dairyapp.network.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("auth/admins")
    suspend fun getAdmins(@Header("Authorization") token: String): List<AdminResponse>

    @GET("auth/admins/{id}")
    suspend fun getAdminDetails(
        @Path("id") adminId: String,
        @Header("Authorization") token: String
    ): AdminDetailsResponse
}
