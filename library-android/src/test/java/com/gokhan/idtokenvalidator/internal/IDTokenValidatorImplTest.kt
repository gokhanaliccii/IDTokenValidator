package com.gokhan.idtokenvalidator.internal

import com.gokhan.idtokenvalidator.Result.INVALID.MalformedRawToken
import com.gokhan.idtokenvalidator.internal.token.JwtTokenFormatValidator
import org.junit.Assert.assertEquals
import org.junit.Test

class IDTokenValidatorImplTest {

    private val tested = IDTokenValidatorImpl(JwtTokenFormatValidator())

    @Test
    fun `if given token is invalid, result be MalformedRawToken`() {
        val givenToken = "token"
        val issuer = "issuer"
        val clientId = "clientId"

        assertEquals(
            MalformedRawToken("$givenToken must have 3 parts"),
            tested.validate(givenToken, issuer, clientId)
        )

    }
}