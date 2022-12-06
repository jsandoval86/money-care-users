package com.moneycare.identity.rest

import com.moneycare.identity.rest.mappers.LoginRequestMapper
import com.moneycare.identity.rest.request.LoginRequest
import com.moneycare.users.LoginUseCase
import com.moneycare.users.token.UserToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(URLIdentity.LOGIN)
class AuthenticationController(
    private var loginRequestMapper: LoginRequestMapper,
    private var loginUseCase: LoginUseCase
) {

    @PostMapping
    fun login(@RequestBody request: LoginRequest): UserToken {
        val command = loginRequestMapper.mapToCommand(request)
        return loginUseCase.execute(command)
    }

}