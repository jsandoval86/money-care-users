package com.moneycare.users

import com.moneycare.identity.IdentityService
import com.moneycare.shared.messages.outbox.repository.OutboxRepository
import com.moneycare.users.password.Password
import com.moneycare.users.rol.Rol
import com.moneycare.users.rol.RolRepository
import com.moneycare.users.shared.exceptions.TechnicalException
import com.moneycare.users.user.SignUpCommand
import com.moneycare.users.user.User
import com.moneycare.users.user.UserRepository
import javax.inject.Named

@Named
class SignUpUserUseCase(
    private var identityService: IdentityService,
    private var userRepository: UserRepository,
    private val rolRepository: RolRepository,
    private var outboxRepository: OutboxRepository
) {

    fun execute(command: SignUpCommand) : User {

        val user = User.createPro(command.name, command.lastName, command.email, command.cellphone)
        val password = Password.of(command.password)

        val identityId = identityService.createUser(user, password)
        saveUser(user, identityId)

        return user
    }

    // TODO: add transacctional
    private fun saveUser(user: User, identityId: String) {
        try {
            user.updateIdentityId(identityId)
            userRepository.save(user)
            rolRepository.addRoleByUser(user, listOf(Rol.pro))
            outboxRepository.save(user.messages)
        } catch (e: Exception) {
            rollbackUserInIdentityService(identityId)
            throw TechnicalException("error creating internal user", e)
        }
    }

    private fun rollbackUserInIdentityService(identityId: String) {
        try{
            identityService.deleteUserById(identityId)
        } catch (e: Exception){
            throw TechnicalException("error deleting user in identity service", e)
        }
    }

}