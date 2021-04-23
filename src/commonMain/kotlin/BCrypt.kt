package de.nycode.bcrypt

/**
 * Hashes an [input] with bcrypt with the specified [cost].
 * @param input the input which will be hashed
 * @param cost the cost used for the bcrypt algorithm. Default is 6.
 */
public expect fun hash(input: String, cost: Int = 6): ByteArray

/**
 * Verifies an [input] to match the [expected] hash with the bcrypt algorithm.
 * @param input the input from the user etc.
 * @param expected the expected byte array stored in the database etc.
 */
public expect fun verify(input: String, expected: ByteArray): Boolean
