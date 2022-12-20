package com.moneycare.identity.client.request

class KeyCloakCreateUserRequest(
    var firstName: String,
    var lastName : String,
    var email    : String,
    var enabled  : Boolean,
    var username : String,
    var credentials : KeyCloakCredentials,
)