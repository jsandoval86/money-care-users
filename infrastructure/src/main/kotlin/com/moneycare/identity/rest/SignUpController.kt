package com.moneycare.identity.rest

import com.moneycare.identity.rest.request.SignUpRequest
import com.moneycare.identity.rest.response.UserResponse
import com.moneycare.users.SignUpUserUseCase
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(URLIdentity.SIGN_UP, consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
class SignUpController(
    private var signUpUserUseCase: SignUpUserUseCase
) {

    @PostMapping
    fun signUp(@RequestBody request: SignUpRequest): UserResponse {
        println(request)
        signUpUserUseCase.execute()
        return UserResponse("name")
    }

}