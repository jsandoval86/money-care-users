package com.moneycare.identity.client.request

class KeyCloakTokenRefreshRequest(
    clientId: String,
    clientSecrets: String,
    grantType: String,
    var refreshToken: String
) : KeyCloakTokenRequestBase(clientId, clientSecrets, grantType)