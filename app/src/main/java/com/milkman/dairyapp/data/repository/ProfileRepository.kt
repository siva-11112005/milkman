package com.milkman.dairyapp.data.repository

import com.milkman.dairyapp.data.dao.CustomerDao
import com.milkman.dairyapp.data.dao.UserDao
import com.milkman.dairyapp.data.entity.UserEntity
import com.milkman.dairyapp.data.model.RepositoryResult
import com.milkman.dairyapp.util.PasswordUtils
import com.milkman.dairyapp.util.TimeUtils

class ProfileRepository(
    private val userDao: UserDao,
    private val customerDao: CustomerDao,
    private val auditRepository: AuditRepository
) {
    suspend fun getUser(userId: Int): UserEntity? = userDao.findById(userId)

    suspend fun updateProfile(
        userId: Int,
        fullName: String,
        phone: String,
        address: String,
        pricePerLiter: Double?,
        actorUserId: Int,
        canEditPrice: Boolean
    ): RepositoryResult {
        val user = userDao.findById(userId) ?: return RepositoryResult(false, "User not found")

        val updatedRows = if (canEditPrice) {
            userDao.updateFullProfile(
                userId = userId,
                fullName = fullName.trim(),
                phone = phone.trim(),
                address = address.trim(),
                pricePerLiter = pricePerLiter
            )
        } else {
            userDao.updateBasicProfile(
                userId = userId,
                fullName = fullName.trim(),
                phone = phone.trim(),
                address = address.trim()
            )
        }

        if (updatedRows <= 0) {
            return RepositoryResult(false, "Unable to update profile")
        }

        if (user.linkedCustomerId != null) {
            val mapped = customerDao.findById(user.linkedCustomerId)
            if (mapped != null) {
                customerDao.update(
                    mapped.copy(
                        name = fullName.trim(),
                        phone = phone.trim(),
                        address = address.trim(),
                        pricePerLiter = if (canEditPrice) pricePerLiter ?: mapped.pricePerLiter else mapped.pricePerLiter,
                        updatedAt = TimeUtils.currentTimestamp(),
                        updatedBy = actorUserId
                    )
                )
            }
        }

        auditRepository.log(
            tableName = "users",
            recordId = userId,
            action = "UPDATE",
            userId = actorUserId,
            details = "Profile updated"
        )
        return RepositoryResult(true, "Profile updated")
    }

    suspend fun changePassword(
        userId: Int,
        currentPassword: String,
        newPassword: String,
        actorUserId: Int
    ): RepositoryResult {
        val user = userDao.findById(userId) ?: return RepositoryResult(false, "User not found")
        val currentHash = PasswordUtils.hash(currentPassword)
        if (currentHash != user.passwordHash) {
            return RepositoryResult(false, "Current password is incorrect")
        }

        val newHash = PasswordUtils.hash(newPassword)
        val updated = userDao.updatePassword(userId, newHash)

        return if (updated > 0) {
            auditRepository.log(
                tableName = "users",
                recordId = userId,
                action = "UPDATE",
                userId = actorUserId,
                details = "Profile password changed"
            )
            RepositoryResult(true, "Password updated")
        } else {
            RepositoryResult(false, "Unable to update password")
        }
    }
}
