package com.moneycare.users

import com.moneycare.identity.IdentityService
import com.moneycare.users.user.LoginCommand
import com.moneycare.users.token.UserToken
import javax.inject.Named

@Named
class LoginUseCase(
    private var identityService: IdentityService
) {
    fun execute(command: LoginCommand): UserToken {
        return identityService.createTokenByUserNameAndPassword(command.username, command.password)
    }

}