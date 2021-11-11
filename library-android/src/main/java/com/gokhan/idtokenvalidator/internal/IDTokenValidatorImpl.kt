package com.gokhan.idtokenvalidator.internal

import com.gokhan.idtokenvalidator.IDTokenValidator
import com.gokhan.idtokenvalidator.Result

internal class IDTokenValidatorImpl : IDTokenValidator {

    override fun validate(token: String, issuer: String, clientId: String): Result {
        TODO("Not yet implemented")
    }
}