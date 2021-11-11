package com.gokhan.idtokenvalidator

import com.gokhan.idtokenvalidator.internal.IDTokenValidatorImpl

object IDTokenValidatorFactory {

    val client: IDTokenValidator = IDTokenValidatorImpl()
}