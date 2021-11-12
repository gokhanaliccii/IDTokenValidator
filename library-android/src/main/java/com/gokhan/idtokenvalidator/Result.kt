package com.gokhan.idtokenvalidator

sealed class Result {
    object VALID : Result()

    sealed class INVALID : Result() {
        data class MalformedRawToken(val message : String) : INVALID()

        data class MissingPropertyInToken(val message : String) : INVALID()

        data class MisMatchedProperty(val message : String) : INVALID()

        object EXPIRED_TOKEN : INVALID()
    }
}