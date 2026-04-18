package com.milkman.dairyapp.util

import java.security.MessageDigest

object PasswordUtils {
    fun hash(raw: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val encoded = digest.digest(raw.toByteArray())
        return encoded.joinToString("") { "%02x".format(it) }
    }

    fun verify(raw: String, expectedHash: String): Boolean {
        return hash(raw) == expectedHash
    }
}
