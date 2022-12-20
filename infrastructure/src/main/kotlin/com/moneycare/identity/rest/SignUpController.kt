package com.moneycare.identity.rest

import com.moneycare.identity.rest.mappers.SignUpRequestMapper
import com.moneycare.identity.rest.mappers.UserResponseMapper
import com.moneycare.identity.rest.request.SignUpRequest
import com.moneycare.identity.rest.response.UserResponse
import com.moneycare.users.SignUpUserUseCase
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(URLIdentity.SIGN_UP, consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
open class SignUpController(
    private var signUpUserUseCase: SignUpUserUseCase,
    private var signUpRequestMapper: SignUpRequestMapper,
    private var userResponseMapper: UserResponseMapper
) {

    @PostMapping
    open fun signUp(@Valid @RequestBody request: SignUpRequest): UserResponse {
        val command = signUpRequestMapper.mapToCommand(request)
        val user = signUpUserUseCase.execute(command)
        return userResponseMapper.mapToResponse(user)
    }

}