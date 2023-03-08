package com.moneycare.users.token

class ValidateUserTokenCommand(
    private val token: String
) {
    companion object {
        fun of(token: String): ValidateUserTokenCommand = ValidateUserTokenCommand(token)
    }

    fun getToken(): String =  token
}