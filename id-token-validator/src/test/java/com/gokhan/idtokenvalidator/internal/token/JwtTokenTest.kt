package com.gokhan.idtokenvalidator.internal.token

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class JwtTokenTest {

    @Test
    fun `given test token, issuer property should be  extracted`() {
        val jwtToken = JwtToken(Fixture.testToken)

        assertEquals("http://server.example.com", jwtToken.issuer)
    }

    @Test
    fun `given test token not contains audience, audience should be empty`() {
        val jwtToken = JwtToken(Fixture.testToken)

        assertTrue(jwtToken.audience.isEmpty())
    }

    private object Fixture {
        const val testToken =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJpc3MiOiJodHRwOi8vc2VydmVyLmV4YW1wbGUuY29tIn0." +
                "ZrLMOHXtKOazopjDi2_OLEa1N25kv2j_hcKiCHSZhUc"
    }
}
