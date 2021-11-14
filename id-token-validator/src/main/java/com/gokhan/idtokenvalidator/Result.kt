package com.gokhan.idtokenvalidator

sealed class Result {
    object VALID : Result()

    sealed class INVALID : Result() {
        data class MalformedRawToken(val message: String) : INVALID()

        data class MissingPropertyInToken(val message: String) : INVALID()

        data class MismatchedProperty(val message: String) : INVALID()
    }
}
