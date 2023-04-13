package com.moneycare.identity.client.request

import com.fasterxml.jackson.annotation.JsonProperty

class KeyCloakCreateUserRequest(
    @JsonProperty("firstName")
    var firstName: String,
    @JsonProperty("lastName")
    var lastName : String,
    @JsonProperty("email")
    var email    : String,
    @JsonProperty("enabled")
    var enabled  : Boolean,
    @JsonProperty("username")
    var username : String,
    @JsonProperty("credentials")
    var credentials : List<KeyCloakCredentials>,
    @JsonProperty("emailVerified")
    var emailVerified: Boolean = true
)