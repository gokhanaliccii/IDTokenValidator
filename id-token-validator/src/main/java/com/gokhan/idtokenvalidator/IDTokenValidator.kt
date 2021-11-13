package com.gokhan.idtokenvalidator

interface IDTokenValidator {

    /**
     *  Validates given token by issuer and clientId according to
     *  https://openid.net/specs/openid-connect-core-1_0.html#IDTokenValidation
     *
     * @param token  base64 encoded jwt token
     * @param issuer the same issuer that used in token creation
     * @param clientId the same clientId used in token creation
     */
    fun validate(
        token: String,
        issuer: String,
        clientId: String,
    ): Result
}
