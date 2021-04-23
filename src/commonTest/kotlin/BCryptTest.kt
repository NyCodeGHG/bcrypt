package de.nycode.bcrypt

import kotlin.js.JsName
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * Root object for using the BCrypt multiplatform library.
 */
object BCryptTest {

    @Test
    @JsName("simple_hash")
    fun `Simple Hash`() {
        val input = "hello"
        val hash = BCrypt.hash(input)
        assertTrue(BCrypt.verify("hello", hash), "Hashes are not matching")
    }

}
