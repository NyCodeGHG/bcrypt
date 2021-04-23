package de.nycode.bcrypt

import at.favre.lib.crypto.bcrypt.BCrypt
import at.favre.lib.crypto.bcrypt.LongPasswordStrategies

private val bcrypt = BCrypt.withDefaults()
private val verifier = BCrypt
    .verifyer(null, LongPasswordStrategies.truncate(BCrypt.Version.VERSION_2A))

public actual fun hash(input: String, cost: Int): ByteArray = bcrypt.hash(cost, input.toByteArray())

public actual fun verify(input: String, expected: ByteArray): Boolean = verifier
    .verify(input.toByteArray(), expected)
    .verified
