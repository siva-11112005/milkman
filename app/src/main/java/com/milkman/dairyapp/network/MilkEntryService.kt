package com.milkman.dairyapp.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface MilkEntryService {
    @GET("milk")
    suspend fun getMilkEntries(
        @Query("date") date: String? = null,
        @Query("customerId") customerId: String? = null,
        @Header("Authorization") token: String
    ): Response<List<Map<String, Any>>>

    @POST("milk")
    suspend fun createMilkEntry(
        @Body request: Map<String, Any>,
        @Header("Authorization") token: String
    ): Response<Map<String, Any>>

    @PUT("milk/{id}")
    suspend fun updateMilkEntry(
        @Path("id") entryId: String,
        @Body request: Map<String, Any>,
        @Header("Authorization") token: String
    ): Response<Map<String, Any>>

    @DELETE("milk/{id}")
    suspend fun deleteMilkEntry(
        @Path("id") entryId: String,
        @Header("Authorization") token: String
    ): Response<Map<String, String>>
}
