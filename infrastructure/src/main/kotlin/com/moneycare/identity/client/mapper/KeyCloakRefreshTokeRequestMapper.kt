package com.moneycare.identity.client.mapper

import com.moneycare.identity.client.request.KeyCloakTokenRefreshRequest
import org.springframework.stereotype.Component

@Component
class KeyCloakRefreshTokeRequestMapper {

    fun mapToRequest(clientId: String, clientSecret: String, grantType: String,
                     refreshToken: String): KeyCloakTokenRefreshRequest{
        return  KeyCloakTokenRefreshRequest(
            clientId, clientSecret, grantType, refreshToken
        )
    }

}