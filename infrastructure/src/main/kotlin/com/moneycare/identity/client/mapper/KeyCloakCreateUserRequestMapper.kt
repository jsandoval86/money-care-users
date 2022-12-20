package com.moneycare.identity.client.mapper

import com.moneycare.identity.client.request.KeyCloakCreateUserRequest
import com.moneycare.identity.client.request.KeyCloakCredentials
import com.moneycare.users.user.User
import org.springframework.stereotype.Component

@Component
class KeyCloakCreateUserRequestMapper {

    fun mapToRequest(user: User, password: String): KeyCloakCreateUserRequest {
        return KeyCloakCreateUserRequest(
            user.getName(), user.getLastName(), user.getEmail().getValue(),
            true, user.getEmail().getValue(), KeyCloakCredentials.createPassword(password)
        )
    }
}