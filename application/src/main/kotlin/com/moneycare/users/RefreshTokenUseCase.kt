package com.moneycare.users

import com.moneycare.identity.IdentityService
import com.moneycare.users.token.UserToken
import com.moneycare.users.user.RefreshTokenCommand
import javax.inject.Named

@Named
class RefreshTokenUseCase(
    private var identityService: IdentityService
) {

    fun execute(command: RefreshTokenCommand): UserToken {
        return identityService.refreshTokenUser(command.refreshToken)
    }
}