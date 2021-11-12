package com.gokhan.idtokenvalidator.internal

import com.gokhan.idtokenvalidator.IDTokenValidator
import com.gokhan.idtokenvalidator.Result
import com.gokhan.idtokenvalidator.Result.INVALID.MalformedRawToken
import com.gokhan.idtokenvalidator.Result.INVALID.MisMatchedProperty
import com.gokhan.idtokenvalidator.Result.INVALID.MissingPropertyInToken
import com.gokhan.idtokenvalidator.internal.token.JwtToken
import com.gokhan.idtokenvalidator.internal.token.JwtTokenFormatValidator

internal class IDTokenValidatorImpl(
    private val tokenFormatValidator: JwtTokenFormatValidator
) : IDTokenValidator {

    override fun validate(rawToken: String, issuer: String, clientId: String): Result {
        if (!tokenFormatValidator.isValid(rawToken)) {
            return MalformedRawToken("$rawToken must have 3 parts")
        }

        val token = JwtToken(rawToken)
        if (token.issuer.isNullOrEmpty()){
            return MissingPropertyInToken("The iss property is not exist the in given token")
        }

        if (issuer != token.issuer){
            return MisMatchedProperty("The iss property mismatched, expected =$issuer actual=${token.issuer}")
        }

        // TODO validate sub, aud, exp and iat according to https://openid.net/specs/openid-connect-core-1_0.html#IDToken

        return Result.VALID
    }
}