package com.milkman.dairyapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.milkman.dairyapp.data.AppDatabase
import com.milkman.dairyapp.data.entity.UserEntity
import com.milkman.dairyapp.data.model.RepositoryResult
import com.milkman.dairyapp.data.repository.AuditRepository
import com.milkman.dairyapp.data.repository.ProfileRepository
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val repository = ProfileRepository(
        userDao = db.userDao(),
        customerDao = db.customerDao(),
        auditRepository = AuditRepository(db.auditLogDao())
    )

    private val _profile = MutableLiveData<UserEntity?>()
    val profile: LiveData<UserEntity?> = _profile

    private val _actionState = MutableLiveData<RepositoryResult>()
    val actionState: LiveData<RepositoryResult> = _actionState

    fun loadProfile(userId: Int) {
        viewModelScope.launch {
            _profile.postValue(repository.getUser(userId))
        }
    }

    fun updateProfile(
        userId: Int,
        fullName: String,
        phone: String,
        address: String,
        pricePerLiter: Double?,
        actorUserId: Int,
        canEditPrice: Boolean
    ) {
        viewModelScope.launch {
            _actionState.postValue(
                repository.updateProfile(
                    userId = userId,
                    fullName = fullName,
                    phone = phone,
                    address = address,
                    pricePerLiter = pricePerLiter,
                    actorUserId = actorUserId,
                    canEditPrice = canEditPrice
                )
            )
            _profile.postValue(repository.getUser(userId))
        }
    }

    fun changePassword(userId: Int, currentPassword: String, newPassword: String, actorUserId: Int) {
        viewModelScope.launch {
            _actionState.postValue(
                repository.changePassword(userId, currentPassword, newPassword, actorUserId)
            )
        }
    }
}
