package com.moneycare.identity.client.mapper

import com.moneycare.identity.client.response.KeyCloakTokenResponse
import com.moneycare.users.token.TokenType
import com.moneycare.users.token.UserToken
import org.springframework.stereotype.Component

@Component
class KeyCloakTokenResponseMapper {

    fun mapToDomain(response: KeyCloakTokenResponse): UserToken {
        val accessToken: String = response.accessToken ?: throw RuntimeException("Error access token is required")
        val expiresIn: Int = response.expiresIn ?: throw RuntimeException("Error expires in is required")
        val refreshToken: String = response.refreshToken ?: throw RuntimeException("Error refresh token is required")
        val refreshExpiresIn: Int = response.refreshExpiresIn ?: throw RuntimeException("Error refresh expires in is required")
        val tokenType: TokenType = TokenType.valueOf(response.tokenType ?: throw RuntimeException("Error token type is required"))

        return UserToken(accessToken, expiresIn, refreshToken, refreshExpiresIn, tokenType)

    }
}