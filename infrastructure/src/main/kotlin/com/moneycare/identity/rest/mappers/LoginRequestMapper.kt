package com.moneycare.identity.rest.mappers

import com.moneycare.identity.rest.request.LoginRequest
import com.moneycare.users.user.LoginCommand
import org.springframework.stereotype.Component

@Component
class LoginRequestMapper {

    fun mapToCommand(request: LoginRequest): LoginCommand {
        return LoginCommand(request.username, request.password)
    }
}