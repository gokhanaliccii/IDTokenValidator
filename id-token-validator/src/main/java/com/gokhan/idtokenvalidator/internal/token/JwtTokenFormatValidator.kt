package com.gokhan.idtokenvalidator.internal.token

internal class JwtTokenFormatValidator {

    fun isValid(rawToken: String): Boolean {
        return EXPECTED_PARTS == rawToken.split(TOKEN_PART_DELIMITER).size
    }

    private companion object {
        const val EXPECTED_PARTS = 3
        const val TOKEN_PART_DELIMITER = "."
    }
}
