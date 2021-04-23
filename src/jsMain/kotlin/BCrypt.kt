package de.nycode.bcrypt

import de.nycode.bcrypt.externals.JsBcrypt
import kotlinx.coroutines.await

public actual fun hash(input: String, cost: Int): ByteArray = JsBcrypt.hashSync(input, cost).encodeToByteArray()

/**
 * Hashes an [input] asynchronously with the specified [cost].
 * @param input the input which will be hashed
 * @param cost the cost used for the bcrypt algorithm. Default is 6.
 */
public suspend fun hashAsync(input: String, cost: Int): ByteArray {
    return JsBcrypt.hash(input, cost).await().encodeToByteArray()
}

/**
 * Verifies an [input] to match the [expected] hash with the bcrypt algorithm.
 * @param input the input from the user etc.
 * @param expected the expected byte array stored in the database etc.
 */
public actual fun verify(input: String, expected: ByteArray): Boolean {
    return JsBcrypt.compareSync(input, expected.decodeToString())
}

/**
 * Verifies an [input] asynchronously to match the [expected] hash with the bcrypt algorithm.
 * @param input the input from the user etc.
 * @param expected the expected byte array stored in the database etc.
 */
public suspend fun verifyAsync(input: String, expected: ByteArray): Boolean {
    return JsBcrypt.compare(input, expected.decodeToString()).await()
}
