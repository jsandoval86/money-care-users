package com.moneycare.users

import com.moneycare.identity.IdentityService
import com.moneycare.users.password.Password
import com.moneycare.users.user.SignUpCommand
import com.moneycare.users.user.User
import javax.inject.Named

@Named
class SignUpUserUseCase(
    private var identityService: IdentityService
) {

    fun execute(command: SignUpCommand) : User {

        val user = User.createPro(command.name, command.lastName, command.email, command.cellphone)
        val password = Password.of(command.password)
        
        identityService.createUser(user, password)

        return user
    }
}