package com.moneycare.identity.client.request

class KeyCloakTokenRequest(
    clientId: String,
    clientSecret: String,
    grantType: String,
    var scope: String,
    var username: String,
    var password: String,
): KeyCloakTokenRequestBase(clientId, clientSecret, grantType)