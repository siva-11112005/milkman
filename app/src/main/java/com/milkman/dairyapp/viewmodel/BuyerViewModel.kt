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

class BuyerViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val repository = CustomerRepository(
        customerDao = db.customerDao(),
        userDao = db.userDao(),
        auditRepository = AuditRepository(db.auditLogDao()),
        syncQueueDao = db.syncQueueDao()
    )

    private val searchText = MutableLiveData("")
    val buyers: LiveData<List<CustomerEntity>> = searchText.switchMap { query ->
        if (query.isBlank()) repository.getBuyers() else repository.searchBuyers(query)
    }

    private val _actionState = MutableLiveData<RepositoryResult>()
    val actionState: LiveData<RepositoryResult> = _actionState

    fun setSearchText(query: String) {
        searchText.value = query
    }

    fun addBuyer(
        name: String,
        phone: String,
        address: String,
        type: String,
        sellingPricePerLiter: Double,
        userId: Int
    ) {
        viewModelScope.launch {
            _actionState.postValue(
                repository.addBuyer(
                    name = name,
                    phone = phone,
                    address = address,
                    type = type,
                    sellingPricePerLiter = sellingPricePerLiter,
                    adminUserId = userId
                )
            )
        }
    }

    fun updateBuyer(
        buyerId: Int,
        name: String,
        phone: String,
        address: String,
        type: String,
        sellingPricePerLiter: Double,
        userId: Int
    ) {
        viewModelScope.launch {
            _actionState.postValue(
                repository.updateBuyer(
                    buyerId = buyerId,
                    name = name,
                    phone = phone,
                    address = address,
                    type = type,
                    sellingPricePerLiter = sellingPricePerLiter,
                    adminUserId = userId
                )
            )
        }
    }

    fun deleteBuyer(
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
