package com.moneycare.users.token

import java.util.UUID

class UserToken(
    var accessToken: String,
    var expiresin: Int,
    var refreshToken: String,
    var refreshExpiresIn: Int,
    var tokenType: TokenType
) {

    companion object{
        fun random(): UserToken {
            return UserToken(UUID.randomUUID().toString(), 100, UUID.randomUUID().toString(), 100, TokenType.Bearer)
        }
    }

}