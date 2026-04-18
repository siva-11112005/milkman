package com.milkman.dairyapp.network

import com.milkman.dairyapp.network.models.AddSupplierRequest
import com.milkman.dairyapp.network.models.AddSupplierResponse
import com.milkman.dairyapp.network.models.CustomerResponse
import com.milkman.dairyapp.network.models.CreateCustomerRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CustomerService {
    @GET("customers")
    suspend fun getSuppliers(
        @Header("Authorization") token: String
    ): Response<List<CustomerResponse>>

    @POST("customers")
    suspend fun createCustomer(
        @Body request: CreateCustomerRequest,
        @Header("Authorization") token: String
    ): Response<AddSupplierResponse>

    @POST("customers")
    suspend fun addSupplier(
        @Body request: AddSupplierRequest,
        @Header("Authorization") token: String
    ): Response<AddSupplierResponse>

    @PUT("customers/{id}")
    suspend fun updateCustomer(
        @Path("id") customerId: String,
        @Body request: Map<String, Any>,
        @Header("Authorization") token: String
    ): Response<AddSupplierResponse>

    @DELETE("customers/{id}")
    suspend fun deleteCustomer(
        @Path("id") customerId: String,
        @Header("Authorization") token: String
    ): Response<Map<String, String>>
}
