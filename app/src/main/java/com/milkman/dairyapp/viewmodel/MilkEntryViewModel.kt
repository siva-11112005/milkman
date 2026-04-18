package com.milkman.dairyapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.milkman.dairyapp.data.AppDatabase
import com.milkman.dairyapp.data.entity.CustomerEntity
import com.milkman.dairyapp.data.model.MilkEntryWithCustomer
import com.milkman.dairyapp.data.model.RepositoryResult
import com.milkman.dairyapp.data.repository.AuditRepository
import com.milkman.dairyapp.data.repository.CustomerRepository
import com.milkman.dairyapp.data.repository.MilkRepository
import com.milkman.dairyapp.util.AppConstants
import com.milkman.dairyapp.util.TimeUtils
import kotlinx.coroutines.launch

data class EntryFilterState(
    val date: String? = null,
    val customerId: Int? = null,
    val customerCategory: String? = null,
    val customerType: String? = null,
    val entryType: String? = null
)

class MilkEntryViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val auditRepository = AuditRepository(db.auditLogDao())
    private val milkRepository = MilkRepository(
        milkEntryDao = db.milkEntryDao(),
        customerDao = db.customerDao(),
        auditRepository = auditRepository,
        syncQueueDao = db.syncQueueDao()
    )
    private val customerRepository = CustomerRepository(
        customerDao = db.customerDao(),
        userDao = db.userDao(),
        auditRepository = auditRepository,
        syncQueueDao = db.syncQueueDao()
    )

    private val filters = MutableLiveData(EntryFilterState())

    val entries: LiveData<List<MilkEntryWithCustomer>> = filters.switchMap { filter ->
        milkRepository.getEntries(
            date = filter.date,
            customerId = filter.customerId,
            customerCategory = filter.customerCategory,
            customerType = filter.customerType,
            entryType = filter.entryType
        )
    }

    val suppliers: LiveData<List<CustomerEntity>> = customerRepository.getSuppliers()
    val buyers: LiveData<List<CustomerEntity>> = customerRepository.getBuyers()
    val customers: LiveData<List<CustomerEntity>> = customerRepository.getAllCustomers()

    private val _actionState = MutableLiveData<RepositoryResult>()
    val actionState: LiveData<RepositoryResult> = _actionState

    fun setFilters(
        date: String? = null,
        customerId: Int? = null,
        customerCategory: String? = null,
        customerType: String? = null,
        entryType: String? = null
    ) {
        filters.value = EntryFilterState(
            date = date,
            customerId = customerId,
            customerCategory = customerCategory,
            customerType = customerType,
            entryType = entryType
        )
    }

    fun addCollectionEntry(
        supplierId: Int,
        date: String,
        session: String,
        quantity: Double,
        userId: Int
    ) {
        addEntry(
            customerId = supplierId,
            date = date,
            session = session,
            entryType = AppConstants.ENTRY_COLLECTION,
            quantity = quantity,
            userId = userId
        )
    }

    fun addDistributionEntry(
        buyerId: Int,
        date: String,
        session: String,
        quantity: Double,
        userId: Int
    ) {
        addEntry(
            customerId = buyerId,
            date = date,
            session = session,
            entryType = AppConstants.ENTRY_DISTRIBUTION,
            quantity = quantity,
            userId = userId
        )
    }

    fun addEntry(
        customerId: Int,
        date: String,
        session: String,
        entryType: String,
        quantity: Double,
        userId: Int
    ) {
        viewModelScope.launch {
            _actionState.postValue(
                milkRepository.addEntry(
                    customerId = customerId,
                    entryDate = date,
                    session = session,
                    entryType = entryType,
                    quantityLiters = quantity,
                    userId = userId
                )
            )
        }
    }

    fun updateEntry(entryId: Int, quantity: Double, userId: Int) {
        viewModelScope.launch {
            _actionState.postValue(milkRepository.updateEntry(entryId, quantity, userId))
        }
    }

    fun deleteEntry(entryId: Int, userId: Int) {
        viewModelScope.launch {
            _actionState.postValue(milkRepository.deleteEntry(entryId, userId))
        }
    }

    fun isLocked(createdAt: Long): Boolean = TimeUtils.isLocked(createdAt)
}
