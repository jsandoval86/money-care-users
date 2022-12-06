package com.moneycare.users.token

import com.moneycare.users.token.TokenType

class UserToken(
    var accessToken: String,
    var expiresin: Int,
    var refreshToken: String,
    var refreshExpiresIn: Int,
    var tokenType: TokenType
)