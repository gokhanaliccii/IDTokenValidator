package com.gokhan.idtokenvalidator

import com.gokhan.idtokenvalidator.internal.IDTokenValidatorImpl
import com.gokhan.idtokenvalidator.internal.token.JwtTokenFormatValidator

object IDTokenValidatorFactory {

    val idTokenValidator: IDTokenValidator = IDTokenValidatorImpl(JwtTokenFormatValidator())
}
