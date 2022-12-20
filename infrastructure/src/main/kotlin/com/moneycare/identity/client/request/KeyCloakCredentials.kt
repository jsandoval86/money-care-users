package com.moneycare.identity.client.request

class KeyCloakCredentials private constructor(
    private var type: String,
    private var value: String,
    private var temporary: Boolean
){

    companion object{
        fun createPassword(value: String): KeyCloakCredentials {
            return KeyCloakCredentials(Type.password.name, value, false)
        }
    }

    enum class Type{
        password
    }

}