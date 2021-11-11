package com.gokhan.idtokenvalidator.internal

import com.gokhan.idtokenvalidator.IDTokenValidator
import com.gokhan.idtokenvalidator.Result
import com.gokhan.idtokenvalidator.internal.token.JwtTokenFormatValidator

internal class IDTokenValidatorImpl(
    private val tokenFormatValidator: JwtTokenFormatValidator
) : IDTokenValidator {

    override fun validate(token: String, issuer: String, clientId: String): Result {
        if (!tokenFormatValidator.isValid(token)) {
            return Result.INVALID.MalformedRawToken("$token must have 3 parts")
        }

        return Result.VALID
    }
}