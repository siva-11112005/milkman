package com.milkman.dairyapp.network

import com.milkman.dairyapp.network.models.AdminResponse
import com.milkman.dairyapp.network.models.CreateAdminRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface StaffService {
    @GET("staff")
    suspend fun getStaff(
        @Header("Authorization") token: String
    ): Response<List<AdminResponse>>

    @POST("staff")
    suspend fun createAdmin(
        @Header("Authorization") token: String,
        @Body request: CreateAdminRequest
    ): Response<AdminResponse>

    @PUT("staff/{id}")
    suspend fun updateStaff(
        @Path("id") staffId: String,
        @Header("Authorization") token: String,
        @Body request: Map<String, Any>
    ): Response<AdminResponse>

    @DELETE("staff/{id}")
    suspend fun deleteStaff(
        @Path("id") staffId: String,
        @Header("Authorization") token: String
    ): Response<Map<String, String>>
}
