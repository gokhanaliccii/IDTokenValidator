package com.gokhan.idtokenvalidator.internal

import com.gokhan.idtokenvalidator.IDTokenValidator
import com.gokhan.idtokenvalidator.Result
import com.gokhan.idtokenvalidator.Result.INVALID.MalformedRawToken
import com.gokhan.idtokenvalidator.Result.INVALID.MismatchedProperty
import com.gokhan.idtokenvalidator.Result.INVALID.MissingPropertyInToken
import com.gokhan.idtokenvalidator.internal.token.JwtToken
import com.gokhan.idtokenvalidator.internal.token.JwtTokenFormatValidator

internal class IDTokenValidatorImpl(
    private val tokenFormatValidator: JwtTokenFormatValidator
) : IDTokenValidator {

    override fun validate(token: String, issuer: String, clientId: String): Result {
        if (!tokenFormatValidator.isValid(token)) {
            return MalformedRawToken("$token must have 3 parts")
        }

        val jwtToken = JwtToken(token)
        if (jwtToken.issuer.isNullOrEmpty()) {
            return MissingPropertyInToken("The iss property is not exist the in given token")
        }

        if (issuer != jwtToken.issuer) {
            return MismatchedProperty("The iss property mismatched, expected =$issuer actual=${jwtToken.issuer}")
        }

        // TODO validate sub, aud, exp and iat according to https://openid.net/specs/openid-connect-core-1_0.html#IDToken

        return Result.VALID
    }
}
