package com.gokhan.idtokenvalidator.internal.token

import android.icu.util.UniversalTimeScale.toLong
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
        issuedAt = (decodedPayload["iat"] as? Double)?.let { seconds -> seconds.secondsToDate() }
        expiresAt = (decodedPayload["exp"] as? Double)?.let { seconds -> seconds.secondsToDate() }
        authorizedParty = decodedPayload["azp"] as String?
        authenticationTime =
            (decodedPayload["auth_time"] as? Double)?.let { seconds -> seconds.secondsToDate() }
        audience = when (val aud = decodedPayload["aud"]) {
            is String -> listOf(aud)
            is List<*> -> aud as List<String>
            else -> emptyList()
        }
    }

    private fun splitToken(token: String): Array<String> =
        token.split(TOKEN_PART_DELIMITER).toTypedArray()

    private fun String.decodeBase64(): String {
        val bytes: ByteArray =
            Base64.decode(this, Base64.URL_SAFE or Base64.NO_WRAP or Base64.NO_PADDING)
        return String(bytes, Charsets.UTF_8)
    }

    private inline fun Double.secondsToDate(): Date {
        return Date(this.toLong() * ONE_SECOND_IN_MILLIS)
    }

    private companion object {
        const val TOKEN_PART_DELIMITER = "."
        const val PAYLOAD_INDEX = 1
        const val ONE_SECOND_IN_MILLIS = 1000
    }
}
