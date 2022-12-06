package com.moneycare.identity

import com.moneycare.users.token.UserToken

interface IdentityService {

    fun createTokenByUserNameAndPassword(username: String, password: String): UserToken

}