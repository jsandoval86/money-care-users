package com.moneycare.users

import com.moneycare.identity.IdentityService
import com.moneycare.users.shared.model.Cellphone
import com.moneycare.users.shared.model.Email
import com.moneycare.users.user.SignUpCommand
import com.moneycare.users.user.User
import javax.inject.Named

@Named
class SignUpUserUseCase(
    private var identityService: IdentityService
) {

    fun execute(command: SignUpCommand) : User {
        val email = Email.of(command.email)
        val cellphone = Cellphone.of(command.cellphone)
        val user = User.create(command.name, command.lastName, email, cellphone)

        identityService.createUser(user, command.password)

        return user
    }
}