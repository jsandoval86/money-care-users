package com.moneycare.identity.client.request

import com.fasterxml.jackson.annotation.JsonProperty

class KeyCloakCredentials private constructor(
    @JsonProperty("type")
    private var type: String,
    @JsonProperty("value")
    private var value: String,
    @JsonProperty("temporary")
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