package com.milkman.dairyapp.data.repository

import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.milkman.dairyapp.data.dao.CustomerDao
import com.milkman.dairyapp.data.dao.SyncQueueDao
import com.milkman.dairyapp.data.dao.UserDao
import com.milkman.dairyapp.data.entity.CustomerEntity
import com.milkman.dairyapp.data.entity.SyncQueueEntity
import com.milkman.dairyapp.data.model.RepositoryResult
import com.milkman.dairyapp.data.model.User
import com.milkman.dairyapp.network.ApiClient
import com.milkman.dairyapp.network.models.AddSupplierRequest
import com.milkman.dairyapp.network.models.CustomerResponse
import com.milkman.dairyapp.util.AppConstants
import com.milkman.dairyapp.util.PasswordUtils
import com.milkman.dairyapp.util.TimeUtils

class CustomerRepository(
    private val customerDao: CustomerDao,
    private val userDao: UserDao,
    private val auditRepository: AuditRepository,
    private val syncQueueDao: SyncQueueDao
) {
    private val gson = Gson()

    fun getAllCustomers(): LiveData<List<CustomerEntity>> = customerDao.getAllCustomers()

    fun getSuppliers(): LiveData<List<CustomerEntity>> =
        customerDao.getCustomersByCategory(AppConstants.CATEGORY_SUPPLIER)

    fun searchSuppliers(query: String): LiveData<List<CustomerEntity>> =
        customerDao.searchCustomersByCategory(AppConstants.CATEGORY_SUPPLIER, query.trim())

    fun getBuyers(): LiveData<List<CustomerEntity>> =
        customerDao.getCustomersByCategory(AppConstants.CATEGORY_BUYER)

    fun searchBuyers(query: String): LiveData<List<CustomerEntity>> =
        customerDao.searchCustomersByCategory(AppConstants.CATEGORY_BUYER, query.trim())

    suspend fun findById(id: Int): CustomerEntity? = customerDao.findById(id)

    suspend fun addSupplier(
        name: String,
        phone: String,
        address: String,
        buyingPricePerLiter: Double,
        username: String,
        password: String,
        adminUserId: Int
    ): RepositoryResult {
        return try {
            val normalizedUsername = username.trim()
            if (userDao.getUserByUsername(normalizedUsername) != null) {
                return RepositoryResult(false, "Username already exists")
            }

            val customerId = customerDao.insert(
                CustomerEntity(
                    name = name.trim(),
                    phone = phone.trim(),
                    address = address.trim(),
                    category = AppConstants.CATEGORY_SUPPLIER,
                    type = "Supplier",
                    pricePerLiter = buyingPricePerLiter,
                    createdAt = TimeUtils.currentTimestamp(),
                    createdBy = adminUserId
                )
            ).toInt()

            val userId = userDao.insert(
                User(
                    fullName = name.trim(),
                    username = normalizedUsername,
                    passwordHash = PasswordUtils.hash(password),
                    role = AppConstants.ROLE_CUSTOMER,
                    createdBy = adminUserId,
                    phone = phone.trim(),
                    address = address.trim(),
                    pricePerLiter = buyingPricePerLiter,
                    customerType = AppConstants.CATEGORY_SUPPLIER,
                    linkedCustomerId = customerId
                )
            ).toInt()

            auditRepository.log(
                tableName = "customers",
                recordId = customerId,
                action = "CREATE",
                userId = adminUserId,
                details = "Supplier profile added: ${name.trim()}"
            )
            auditRepository.log(
                tableName = "users",
                recordId = userId,
                action = "CREATE",
                userId = adminUserId,
                details = "Supplier account created: $normalizedUsername"
            )

            // Queue TWO separate API calls for sync
            
            // 1. Create customer via POST /api/customers
            val customerPayload = com.milkman.dairyapp.network.models.CreateCustomerRequest(
                name = name.trim(),
                phone = phone.trim(),
                address = address.trim(),
                type = "Supplier",
                pricePerLiter = buyingPricePerLiter
            )
            syncQueueDao.insert(
                SyncQueueEntity(
                    operationType = "CREATE_CUSTOMER",
                    tableName = "customers",
                    recordId = customerId,
                    payload = gson.toJson(customerPayload)
                )
            )

            // 2. Create staff/user via POST /api/staff
            val staffPayload = com.milkman.dairyapp.network.models.CreateAdminRequest(
                username = normalizedUsername,
                password = password,
                fullName = name.trim()
            )
            syncQueueDao.insert(
                SyncQueueEntity(
                    operationType = "CREATE_STAFF",
                    tableName = "users",
                    recordId = userId,
                    payload = gson.toJson(staffPayload)
                )
            )

            RepositoryResult(
                true,
                "✅ Saved locally. Will sync to MongoDB Atlas when online 🔄",
                customerId.toLong()
            )
        } catch (e: Exception) {
            RepositoryResult(false, e.message ?: "Unable to save supplier")
        }
    }

    suspend fun addBuyer(
        name: String,
        phone: String,
        address: String,
        type: String,
        sellingPricePerLiter: Double,
        adminUserId: Int
    ): RepositoryResult {
        return try {
            val customerId = customerDao.insert(
                CustomerEntity(
                    name = name.trim(),
                    phone = phone.trim(),
                    address = address.trim(),
                    category = AppConstants.CATEGORY_BUYER,
                    type = type,
                    pricePerLiter = sellingPricePerLiter,
                    createdAt = TimeUtils.currentTimestamp(),
                    createdBy = adminUserId
                )
            ).toInt()

            auditRepository.log(
                tableName = "customers",
                recordId = customerId,
                action = "CREATE",
                userId = adminUserId,
                details = "Buyer added: ${name.trim()} ($type)"
            )
            RepositoryResult(true, "Buyer saved", customerId.toLong())
        } catch (e: Exception) {
            RepositoryResult(false, e.message ?: "Unable to save buyer")
        }
    }

    suspend fun updateSupplier(
        supplierId: Int,
        name: String,
        phone: String,
        address: String,
        buyingPricePerLiter: Double,
        adminUserId: Int
    ): RepositoryResult {
        val existing = customerDao.findById(supplierId) ?: return RepositoryResult(false, "Supplier not found")
        if (existing.category != AppConstants.CATEGORY_SUPPLIER) {
            return RepositoryResult(false, "Selected profile is not a supplier")
        }

        return try {
            customerDao.update(
                existing.copy(
                    name = name.trim(),
                    phone = phone.trim(),
                    address = address.trim(),
                    pricePerLiter = buyingPricePerLiter
                )
            )

            auditRepository.log(
                tableName = "customers",
                recordId = supplierId,
                action = "UPDATE",
                userId = adminUserId,
                details = "Supplier updated: ${name.trim()}"
            )
            RepositoryResult(true, "Supplier updated successfully")
        } catch (e: Exception) {
            RepositoryResult(false, e.message ?: "Unable to update supplier")
        }
    }

    suspend fun updateBuyer(
        buyerId: Int,
        name: String,
        phone: String,
        address: String,
        type: String,
        sellingPricePerLiter: Double,
        adminUserId: Int
    ): RepositoryResult {
        val existing = customerDao.findById(buyerId) ?: return RepositoryResult(false, "Buyer not found")
        if (existing.category != AppConstants.CATEGORY_BUYER) {
            return RepositoryResult(false, "Selected profile is not a buyer")
        }

        return try {
            customerDao.update(
                existing.copy(
                    name = name.trim(),
                    phone = phone.trim(),
                    address = address.trim(),
                    type = type,
                    pricePerLiter = sellingPricePerLiter
                )
            )

            auditRepository.log(
                tableName = "customers",
                recordId = buyerId,
                action = "UPDATE",
                userId = adminUserId,
                details = "Buyer updated: ${name.trim()}"
            )
            RepositoryResult(true, "Buyer updated successfully")
        } catch (e: Exception) {
            RepositoryResult(false, e.message ?: "Unable to update buyer")
        }
    }

    suspend fun deleteCustomer(
        customer: CustomerEntity,
        adminUserId: Int,
        authToken: String
    ): RepositoryResult {
        if (authToken.isBlank()) {
            return RepositoryResult(false, "Authentication token missing")
        }

        return try {
            val authHeader = "Bearer $authToken"
            val remoteCustomersResponse = ApiClient.customerService.getSuppliers(authHeader)
            if (!remoteCustomersResponse.isSuccessful) {
                return RepositoryResult(
                    false,
                    parseServerMessage(
                        remoteCustomersResponse.errorBody()?.string(),
                        "Unable to fetch customers from server"
                    )
                )
            }

            val remoteCustomer = findMatchingRemoteCustomer(remoteCustomersResponse.body().orEmpty(), customer)
                ?: return RepositoryResult(false, "Customer not found on server")

            val linkedUser = userDao.findByLinkedCustomerId(customer.id)
            if (linkedUser != null) {
                val staffResponse = ApiClient.staffService.getStaff(authHeader)
                if (!staffResponse.isSuccessful) {
                    return RepositoryResult(
                        false,
                        parseServerMessage(
                            staffResponse.errorBody()?.string(),
                            "Unable to verify assigned users"
                        )
                    )
                }

                val remoteLinkedUser = staffResponse.body().orEmpty()
                    .firstOrNull { it.username.equals(linkedUser.username, ignoreCase = true) }

                if (remoteLinkedUser != null) {
                    val deleteUserResponse = ApiClient.staffService.deleteStaff(remoteLinkedUser.id, authHeader)
                    if (!deleteUserResponse.isSuccessful) {
                        return RepositoryResult(
                            false,
                            parseServerMessage(
                                deleteUserResponse.errorBody()?.string(),
                                "Unable to delete assigned user"
                            )
                        )
                    }
                }
            }

            val deleteCustomerResponse = ApiClient.customerService.deleteCustomer(remoteCustomer._id, authHeader)
            if (!deleteCustomerResponse.isSuccessful) {
                return RepositoryResult(
                    false,
                    parseServerMessage(
                        deleteCustomerResponse.errorBody()?.string(),
                        "Unable to delete customer"
                    )
                )
            }

            customerDao.delete(customer)
            linkedUser?.let {
                userDao.deleteById(it.id)
                auditRepository.log(
                    tableName = "users",
                    recordId = it.id,
                    action = "DELETE",
                    userId = adminUserId,
                    details = "Assigned user deleted with supplier: ${it.username}"
                )
            }

            auditRepository.log(
                tableName = "customers",
                recordId = customer.id,
                action = "DELETE",
                userId = adminUserId,
                details = "Customer deleted: ${customer.name}"
            )

            RepositoryResult(true, "Customer deleted successfully")
        } catch (e: Exception) {
            RepositoryResult(false, e.message ?: "Unable to delete customer")
        }
    }

    private fun findMatchingRemoteCustomer(
        remoteCustomers: List<CustomerResponse>,
        localCustomer: CustomerEntity
    ): CustomerResponse? {
        val name = localCustomer.name.trim()
        val phone = localCustomer.phone.trim()
        val type = localCustomer.type.trim()

        return remoteCustomers.firstOrNull {
            it.phone.trim().equals(phone, ignoreCase = true) &&
                it.name.trim().equals(name, ignoreCase = true) &&
                (it.type?.trim()?.equals(type, ignoreCase = true) ?: true)
        } ?: remoteCustomers.firstOrNull {
            it.phone.trim().equals(phone, ignoreCase = true) &&
                (it.type?.trim()?.equals(type, ignoreCase = true) ?: true)
        }
    }

    private fun parseServerMessage(errorBody: String?, fallbackMessage: String): String {
        if (errorBody.isNullOrBlank()) {
            return fallbackMessage
        }

        return try {
            val parsed = gson.fromJson(errorBody, Map::class.java)
            val message = parsed["message"]?.toString()?.trim().orEmpty()
            if (message.isBlank()) fallbackMessage else message
        } catch (_: Exception) {
            fallbackMessage
        }
    }
}
