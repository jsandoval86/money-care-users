package com.moneycare.identity

import com.moneycare.users.token.UserToken
import com.moneycare.users.user.User

interface IdentityService {

    fun createTokenByUserNameAndPassword(username: String, password: String): UserToken

    fun createUser(user: User, password: String)

    fun refreshTokenUser(refreshToken: String): UserToken
}