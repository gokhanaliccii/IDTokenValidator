package com.gokhan.idtokenvalidator.internal

import com.gokhan.idtokenvalidator.Result.INVALID.MalformedRawToken
import com.gokhan.idtokenvalidator.Result.INVALID.MisMatchedProperty
import com.gokhan.idtokenvalidator.Result.INVALID.MissingPropertyInToken
import com.gokhan.idtokenvalidator.internal.token.JwtTokenFormatValidator
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest= Config.NONE)
@RunWith(RobolectricTestRunner::class)
class IDTokenValidatorImplTest {

    private val tested = IDTokenValidatorImpl(JwtTokenFormatValidator())

    @Test
    fun `if given token is invalid, result should be MalformedRawToken`() {
        val givenToken = "token"
        val issuer = "issuer"
        val clientId = "clientId"

        assertEquals(
            MalformedRawToken("$givenToken must have 3 parts"),
            tested.validate(givenToken, issuer, clientId)
        )
    }

    @Test
    fun `if given token's iss property not exist, result should be MissingPropertyInToken`() {
        val givenToken =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
        val issuer = "issuer"
        val clientId = "clientId"

        assertEquals(
            MissingPropertyInToken("The iss property is not exist the in given token"),
            tested.validate(givenToken, issuer, clientId)
        )
    }

    @Test
    fun `if given token's iss field not matches with issuer, result should be MissingPropertyInToken`() {
        val givenToken =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ0ZXN0Iiwic3ViIjoiMTIzNDU2Nzg5MCIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMn0.jiHexPW_MaO4O-lWRqqTlc_qhELWfJ-yzoALLmyavTY"
        val issuer = "issuer"
        val clientId = "clientId"

        assertEquals(
            MisMatchedProperty("The iss property mismatched, expected =$issuer actual=test"),
            tested.validate(givenToken, issuer, clientId)
        )
    }
}