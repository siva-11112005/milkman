package com.milkman.dairyapp.network.models

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val user: UserResponse
)

data class UserResponse(
    val id: String,
    val username: String,
    val fullName: String,
    val role: String,
    val phone: String? = null,
    val address: String? = null,
    val linkedCustomerId: String? = null,
    val pricePerLiter: Double? = null
)

data class AdminResponse(
    val id: String,
    val username: String,
    val fullName: String,
    val role: String,
    val isActive: Boolean,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val assignedUsersCount: Int = 0,
    val assignedCustomersCount: Int = 0,
    val permissions: Map<String, Boolean> = emptyMap()
)

data class AdminDetailsResponse(
    val id: String,
    val username: String,
    val fullName: String,
    val role: String,
    val isActive: Boolean,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val createdBy: CreatedByResponse? = null,
    val permissions: Map<String, Boolean> = emptyMap(),
    val assignedUsersCount: Int = 0,
    val assignedCustomersCount: Int = 0,
    val assignedUsers: List<AdminManagedUser> = emptyList(),
    val assignedCustomers: List<AdminManagedCustomer> = emptyList()
)

data class CreatedByResponse(
    val id: String,
    val username: String,
    val fullName: String,
    val role: String
)

data class AdminManagedUser(
    val id: String,
    val username: String,
    val fullName: String,
    val role: String,
    val isActive: Boolean,
    val createdAt: String? = null,
    val updatedAt: String? = null
)

data class AdminManagedCustomer(
    val id: String,
    val name: String,
    val phone: String,
    val address: String? = null,
    val type: String? = null,
    val pricePerLiter: Double? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
)

data class CreateAdminRequest(
    val username: String,
    val password: String,
    val fullName: String
)

data class CreateCustomerRequest(
    val name: String,
    val phone: String,
    val address: String,
    val type: String,
    val pricePerLiter: Double? = null
)

data class AddSupplierRequest(
    val name: String,
    val phone: String,
    val address: String,
    val buyingPricePerLiter: Double,
    val username: String,
    val password: String
)

data class AddSupplierResponse(
    val success: Boolean,
    val message: String,
    val customerId: String? = null,
    val userId: String? = null
)

data class CustomerResponse(
    val _id: String,
    val name: String,
    val phone: String,
    val address: String? = null,
    val category: String? = null,
    val type: String? = null,
    val pricePerLiter: Double? = null,
    val createdAt: String? = null,
    val createdBy: String? = null
)

data class GetCustomersResponse(
    val success: Boolean,
    val data: List<CustomerResponse>? = null
)
