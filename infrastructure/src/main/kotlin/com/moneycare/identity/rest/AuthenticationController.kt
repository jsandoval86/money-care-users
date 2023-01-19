package com.moneycare.identity.rest

import com.moneycare.identity.rest.mappers.LoginRequestMapper
import com.moneycare.identity.rest.request.LoginRequest
import com.moneycare.identity.rest.request.RefreshRequest
import com.moneycare.users.LoginUseCase
import com.moneycare.users.RefreshTokenUseCase
import com.moneycare.users.token.UserToken
import com.moneycare.users.user.RefreshTokenCommand
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class AuthenticationController(
    private var loginRequestMapper: LoginRequestMapper,
    private var loginUseCase: LoginUseCase,
    private var refreshTokenUseCase: RefreshTokenUseCase
) {

    @PostMapping(URLIdentityV1.LOGIN)
    fun login(@RequestBody request: LoginRequest): UserToken {
        val command = loginRequestMapper.mapToCommand(request)
        return loginUseCase.execute(command)
    }

    @PostMapping(URLIdentityV1.REFRESH_TOKEN)
    fun refresh(@RequestBody request: RefreshRequest): UserToken{
        val command = RefreshTokenCommand(request.token)
        return refreshTokenUseCase.execute(command)
    }

}