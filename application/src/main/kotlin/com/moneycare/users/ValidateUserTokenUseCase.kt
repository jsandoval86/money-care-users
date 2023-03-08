package com.moneycare.users

import com.moneycare.identity.IdentityService
import com.moneycare.users.token.ValidateUserTokenCommand
import javax.inject.Named

@Named
class ValidateUserTokenUseCase(
    private val identityService: IdentityService
) {

    fun execute(command: ValidateUserTokenCommand) {
        identityService.validateToken(command.getToken())
    }

}