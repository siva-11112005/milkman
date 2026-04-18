package com.milkman.dairyapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val BASE_URL = "https://milkman-wjb4.onrender.com/api/"
    @Volatile
    private var adminScopeId: String? = null

    fun setAdminScope(adminId: String?) {
        adminScopeId = adminId?.trim()?.takeIf { it.isNotBlank() }
    }

    fun clearAdminScope() {
        adminScopeId = null
    }

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
                .header("Content-Type", "application/json")

            adminScopeId?.let {
                requestBuilder.header("X-Admin-Scope-Id", it)
            }

            val newRequest = requestBuilder.build()
            chain.proceed(newRequest)
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val authService: AuthService = retrofit.create(AuthService::class.java)
    val customerService: CustomerService = retrofit.create(CustomerService::class.java)
    val staffService: StaffService = retrofit.create(StaffService::class.java)
    val milkEntryService: MilkEntryService = retrofit.create(MilkEntryService::class.java)
}
