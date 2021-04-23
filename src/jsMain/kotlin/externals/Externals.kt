package de.nycode.bcrypt.externals

import kotlin.js.Promise

@JsModule("bcrypt")
@JsNonModule
internal external object JsBcrypt {
    fun genSaltSync(rounds: Number = definedExternally, minor: String = definedExternally): String

    fun genSalt(
        rounds: Number = definedExternally,
        callback: (err: Error, salt: String) -> Unit = definedExternally
    ): Promise<String>

    fun genSalt(): Promise<String>

    fun genSalt(rounds: Number = definedExternally): Promise<String>

    fun genSalt(
        rounds: Number = definedExternally,
        minor: String = definedExternally,
        callback: (err: Error, salt: String) -> Unit = definedExternally
    ): Promise<String>

    fun genSalt(rounds: Number = definedExternally, minor: String = definedExternally): Promise<String>

    fun genSalt(callback: (err: Error, salt: String) -> Unit = definedExternally): Promise<String>

    fun hashSync(data: Any, saltOrRounds: String): String

    fun hashSync(data: Any, saltOrRounds: Number): String

    fun hash(
        data: Any,
        saltOrRounds: String,
        callback: (err: Error, encrypted: String) -> Unit = definedExternally
    ): Promise<String>

    fun hash(data: Any, saltOrRounds: String): Promise<String>

    fun hash(
        data: Any,
        saltOrRounds: Number,
        callback: (err: Error, encrypted: String) -> Unit = definedExternally
    ): Promise<String>

    fun hash(data: Any, saltOrRounds: Number): Promise<String>

    fun compareSync(data: Any, encrypted: String): Boolean

    fun compare(
        data: Any,
        encrypted: String,
        callback: (err: Error, same: Boolean) -> Unit = definedExternally
    ): Promise<Boolean>

    fun getRounds(encrypted: String): Number
}
