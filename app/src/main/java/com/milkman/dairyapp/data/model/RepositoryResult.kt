package com.milkman.dairyapp.data.model

data class RepositoryResult(
    val success: Boolean,
    val message: String,
    val recordId: Long? = null
)
