package com.gokhan.idtokenvalidator.internal.token

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class JwtTokenFormatValidatorTest {

    private val tested = JwtTokenFormatValidator()

    @Test
    fun `given 1 parts token, should be invalid`() {
        assertFalse(tested.isValid("header"))
    }

    @Test
    fun `given 2 parts should be invalid`() {
        assertFalse(tested.isValid("header.payload"))
    }

    @Test
    fun `given 3 parts should be valid`() {
        assertTrue(tested.isValid("header.payload.signature"))
    }

    @Test
    fun `given mis formatted token should be invalid`() {
        assertFalse(tested.isValid("header.payload.signature.."))
    }
}