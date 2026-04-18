package com.milkman.dairyapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.milkman.dairyapp.data.AppDatabase
import com.milkman.dairyapp.data.entity.CustomerEntity
import com.milkman.dairyapp.data.model.RepositoryResult
import com.milkman.dairyapp.data.repository.AuditRepository
import com.milkman.dairyapp.data.repository.CustomerRepository
import kotlinx.coroutines.launch

class CustomerViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val repository = CustomerRepository(
        customerDao = db.customerDao(),
        userDao = db.userDao(),
        auditRepository = AuditRepository(db.auditLogDao()),
        syncQueueDao = db.syncQueueDao()
    )

    private val searchText = MutableLiveData("")
    val customers: LiveData<List<CustomerEntity>> = searchText.switchMap { query ->
        if (query.isBlank()) repository.getSuppliers() else repository.searchSuppliers(query)
    }

    private val _actionState = MutableLiveData<RepositoryResult>()
    val actionState: LiveData<RepositoryResult> = _actionState

    fun setSearchText(query: String) {
        searchText.value = query
    }

    fun addSupplier(
        name: String,
        phone: String,
        address: String,
        buyingPricePerLiter: Double,
        username: String,
        password: String,
        userId: Int
    ) {
        viewModelScope.launch {
            _actionState.postValue(
                repository.addSupplier(
                    name = name,
                    phone = phone,
                    address = address,
                    buyingPricePerLiter = buyingPricePerLiter,
                    username = username,
                    password = password,
                    adminUserId = userId
                )
            )
        }
    }

    fun updateSupplier(
        supplierId: Int,
        name: String,
        phone: String,
        address: String,
        buyingPricePerLiter: Double,
        userId: Int
    ) {
        viewModelScope.launch {
            _actionState.postValue(
                repository.updateSupplier(
                    supplierId = supplierId,
                    name = name,
                    phone = phone,
                    address = address,
                    buyingPricePerLiter = buyingPricePerLiter,
                    adminUserId = userId
                )
            )
        }
    }

    fun deleteSupplier(
        item: CustomerEntity,
        userId: Int,
        authToken: String
    ) {
        viewModelScope.launch {
            _actionState.postValue(
                repository.deleteCustomer(
                    customer = item,
                    adminUserId = userId,
                    authToken = authToken
                )
            )
        }
    }
}
