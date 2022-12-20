package com.moneycare.identity.rest.mappers

import com.moneycare.identity.rest.request.SignUpRequest
import com.moneycare.users.user.SignUpCommand
import org.springframework.stereotype.Component

@Component
class SignUpRequestMapper {

    fun mapToCommand(request: SignUpRequest): SignUpCommand = SignUpCommand(
        request.name, request.lastName, request.email, request.cellphone, request.password
    )

}