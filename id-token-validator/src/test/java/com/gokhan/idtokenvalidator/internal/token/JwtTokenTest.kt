package com.gokhan.idtokenvalidator.internal.token

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest= Config.NONE)
@RunWith(RobolectricTestRunner::class)
class JwtTokenTest {

    @Test
    fun `given test token, issuer property should be  extracted`() {
        val jwtToken = JwtToken(Fixture.testToken)

        assertEquals("http://server.example.com1", jwtToken.issuer)
    }

    @Test
    fun `given test token not contains audience, audience should be empty`() {
        val jwtToken = JwtToken(Fixture.testToken)

        Assert.assertTrue(jwtToken.audience.isEmpty())
    }

    private object Fixture {
        const val testToken = "eyJraWQiOiIxZTlnZGs3IiwiYWxnIjoiUlMyNTYifQ.ewogImlz\n" +
                "    cyI6ICJodHRwOi8vc2VydmVyLmV4YW1wbGUuY29tIiwKICJzdWIiOiAiMjQ4\n" +
                "    Mjg5NzYxMDAxIiwKICJhdWQiOiAiczZCaGRSa3F0MyIsCiAibm9uY2UiOiAi\n" +
                "    bi0wUzZfV3pBMk1qIiwKICJleHAiOiAxMzExMjgxOTcwLAogImlhdCI6IDEz\n" +
                "    MTEyODA5NzAsCiAibmFtZSI6ICJKYW5lIERvZSIsCiAiZ2l2ZW5fbmFtZSI6\n" +
                "    ICJKYW5lIiwKICJmYW1pbHlfbmFtZSI6ICJEb2UiLAogImdlbmRlciI6ICJm\n" +
                "    ZW1hbGUiLAogImJpcnRoZGF0ZSI6ICIwMDAwLTEwLTMxIiwKICJlbWFpbCI6\n" +
                "    ICJqYW5lZG9lQGV4YW1wbGUuY29tIiwKICJwaWN0dXJlIjogImh0dHA6Ly9l\n" +
                "    eGFtcGxlLmNvbS9qYW5lZG9lL21lLmpwZyIKfQ.rHQjEmBqn9Jre0OLykYNn\n" +
                "    spA10Qql2rvx4FsD00jwlB0Sym4NzpgvPKsDjn_wMkHxcp6CilPcoKrWHcip\n" +
                "    R2iAjzLvDNAReF97zoJqq880ZD1bwY82JDauCXELVR9O6_B0w3K-E7yM2mac\n" +
                "    AAgNCUwtik6SjoSUZRcf-O5lygIyLENx882p6MtmwaL1hd6qn5RZOQ0TLrOY\n" +
                "    u0532g9Exxcm-ChymrB4xLykpDj3lUivJt63eEGGN6DH5K6o33TcxkIjNrCD\n" +
                "    4XB1CKKumZvCedgHHF3IAK4dVEDSUoGlH9z4pP_eWYNXvqQOjGs-rDaQzUHl\n" +
                "    6cQQWNiDpWOl_lxXjQEvQ\n" +
                "    &state=af0ifjsldkj"

    }
}
