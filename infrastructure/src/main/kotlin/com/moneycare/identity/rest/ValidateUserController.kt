package com.moneycare.identity.rest

import com.moneycare.users.ValidateUserTokenUseCase
import com.moneycare.users.token.ValidateUserTokenCommand
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class ValidateUserController(
    private val validateUserTokenUseCase: ValidateUserTokenUseCase
) {

    @GetMapping(URLIdentityV1.VALIDATE_TOKEN)
    fun validate( @RequestHeader("Authorization") token: String ) {
        validateUserTokenUseCase.execute(
            ValidateUserTokenCommand.of(token)
        )
    }

}