package com.gokhan.idtokenvalidator.internal.token

import android.util.Base64
import com.gokhan.idtokenvalidator.internal.util.toJsonObject
import com.gokhan.idtokenvalidator.internal.util.toMap
import java.util.Date

/**
 *  Inspired from
 *  https://github.com/auth0/Auth0.Android/blob/main/auth0/src/main/java/com/auth0/android/request/internal/Jwt.kt
 */
internal class JwtToken(rawToken : String) {

    private val decodedPayload: Map<String, Any?>
    private val parts: Array<String>

    // payload
    val subject: String?
    val issuer: String?
    val nonce: String?
    val organizationId: String?
    val issuedAt: Date?
    val expiresAt: Date?
    val authorizedParty: String?
    val authenticationTime: Date?
    val audience: List<String>

    init {
        parts = splitToken(rawToken)
        val jsonPayload = parts[PAYLOAD_INDEX].decodeBase64()
        decodedPayload = jsonPayload.toJsonObject().toMap()

        // payload properties
        subject = decodedPayload["sub"] as String?
        issuer = decodedPayload["iss"] as String?
        nonce = decodedPayload["nonce"] as String?
        organizationId = decodedPayload["org_id"] as String?
        issuedAt = (decodedPayload["iat"] as? Double)?.let { Date(it.toLong() * 1000) }
        expiresAt = (decodedPayload["exp"] as? Double)?.let { Date(it.toLong() * 1000) }
        authorizedParty = decodedPayload["azp"] as String?
        authenticationTime =
            (decodedPayload["auth_time"] as? Double)?.let { Date(it.toLong() * 1000) }
        audience = when (val aud = decodedPayload["aud"]) {
            is String -> listOf(aud)
            is List<*> -> aud as List<String>
            else -> emptyList()
        }
    }

    private fun splitToken(token: String): Array<String> {
        var parts = token.split(TOKEN_PART_DELIMITER).toTypedArray()
        if (parts.size != EXPECTED_PARTS) {
            throw IllegalArgumentException(
                String.format(
                    "The token was expected to have 3 parts, but got %s.",
                    parts.size
                )
            )
        }
        return parts
    }

    private fun String.decodeBase64(): String {
        val bytes: ByteArray =
            Base64.decode(this, Base64.URL_SAFE or Base64.NO_WRAP or Base64.NO_PADDING)
        return String(bytes, Charsets.UTF_8)
    }

    private companion object {
        const val EXPECTED_PARTS =3
        const val TOKEN_PART_DELIMITER = "."
        const val PAYLOAD_INDEX = 1
    }
}